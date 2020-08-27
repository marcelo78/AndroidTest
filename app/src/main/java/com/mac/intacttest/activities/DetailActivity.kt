package com.mac.intacttest.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.mac.intacttest.R
import com.mac.intacttest.activities.data.ProductsCollection.Article
import com.mac.intacttest.activities.fragments.ItemListActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {

    private val picasso: Picasso by inject()
    private lateinit var objProduct: Article
    private var btnAction = ""

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val gson = Gson()
            val intent = Intent(v.context, MainActivity::class.java).apply {
                putExtra(MainActivity.ARG_ITEM, gson.toJson(objProduct))
            }
            if (btnAction == ARG_ACTION) {
                setResult(MainActivity.ACTION_ADD, intent)
            } else {
                setResult(MainActivity.ACTION_REMOVE, intent)
            }
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            val gson = Gson()
            objProduct =
                gson.fromJson(intent.getStringExtra(MainActivity.ARG_ITEM), Article::class.java)

            btnAction = intent.getStringExtra(MainActivity.ARG_ITEM_ACTION) ?: ""
            if (btnAction == ARG_ACTION) {
                btnDelete.visibility = View.GONE
            } else {
                btnAdd.visibility = View.GONE
            }

            supportActionBar?.title = objProduct.author

            picasso.load(objProduct.urlToImage)
                .fit()
                .centerCrop()
                .into(imageViewProductDetail)
            txtDescription.text = objProduct.description

        }

        setup()
    }

    fun setup() {

        btnAdd.setOnClickListener(onClickListener)
        btnDelete.setOnClickListener(onClickListener)

    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                navigateUpTo(Intent(this, ItemListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    companion object {

        const val ARG_ACTION = "A"

    }


}
