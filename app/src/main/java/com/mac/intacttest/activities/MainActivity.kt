package com.mac.intacttest.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mac.intacttest.R
import com.mac.intacttest.activities.adapter.ProductsAdapter
import com.mac.intacttest.activities.adapter.WishProductAdapter
import com.mac.intacttest.activities.data.ProductsCollection
import com.mac.intacttest.activities.viewmodel.ProductsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val newsViewModel: ProductsViewModel by viewModel()
    private val picasso: Picasso by inject()

    private var listwish: ArrayList<ProductsCollection.Article> = arrayListOf()

    private lateinit var adapterWish: WishProductAdapter

    val positiveButtonClick = { _: DialogInterface, _: Int ->
        listwish.clear()
        adapterWish.updateData(listwish)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val adapter = ProductsAdapter(picasso)
        adapterWish = WishProductAdapter(picasso)
        recycler_view.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL, false
            )
            this.adapter = adapter
        }
        recyclerViewWish.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL, false
            )
            this.adapter = adapterWish
        }

        newsViewModel.uiState.observe(this, Observer {
            val dataState = it ?: return@Observer
            progress_bar.visibility = if (dataState.showProgress) View.VISIBLE else View.GONE
            Log.d("newsViewModel.observe", dataState.toString())
            if (dataState.article != null && !dataState.article.consumed) {
                dataState.article.consume()?.let { article ->
                    println(article)
                    adapter.submitList(article)
                }
            }
            if (dataState.error != null && !dataState.error.consumed)
                dataState.error.consume()?.let { errorResource ->
                    // show error
                    Toast.makeText(this, resources.getString(errorResource), Toast.LENGTH_SHORT)
                        .show()
                }
        })

        btnProceedCheck.setOnClickListener { _ ->
            createDialog()
        }
    }

    private fun createDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(getString(R.string.message_confirm))

        builder.setPositiveButton(android.R.string.yes, positiveButtonClick)
        builder.setNegativeButton(android.R.string.no) { _, _ ->
        }

        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            val result: String = data.getStringExtra(ARG_ITEM) ?: ""
            val gson = Gson()
            val product = gson.fromJson(result, ProductsCollection.Article::class.java)
            if (requestCode == ACTION_ADD) {
                listwish.add(product)
            } else if (requestCode == ACTION_REMOVE) {
                listwish.remove(product)
            }
            adapterWish.updateData(listwish)

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {

        const val ARG_ITEM = "item_id"
        const val ARG_ITEM_ACTION = "action"
        const val ACTION_ADD = 1
        const val ACTION_REMOVE = 2

    }

}
