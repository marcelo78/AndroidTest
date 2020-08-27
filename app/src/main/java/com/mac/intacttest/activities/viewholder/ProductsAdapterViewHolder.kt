package com.mac.intacttest.activities.viewholder

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.mac.intacttest.R
import com.mac.intacttest.activities.DetailActivity
import com.mac.intacttest.activities.MainActivity
import com.mac.intacttest.activities.data.ProductsCollection
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer

class ProductsAdapterViewHolder constructor(
    override val containerView: View, private val picasso: Picasso
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(product: ProductsCollection.Article) {
        containerView.findViewById<ImageView>(R.id.news_image_view)
        picasso.load(product.urlToImage)
            .fit()
            .centerCrop()
            .into(containerView.findViewById<ImageView>(R.id.news_image_view))
        containerView.findViewById<TextView>(R.id.tvNewsTitle).text = product.title

        with(containerView) {
            tag = product
            setOnClickListener(onClickListener)
        }
    }

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as ProductsCollection.Article
            val gson = Gson()
            val intent = Intent(v.context, DetailActivity::class.java).apply {
                putExtra(MainActivity.ARG_ITEM, gson.toJson(item))
                putExtra(MainActivity.ARG_ITEM_ACTION, DetailActivity.ARG_ACTION)
            }
            val context: MainActivity = v.context as MainActivity
            context.startActivityForResult(intent, MainActivity.ACTION_ADD)

        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            picasso: Picasso
        ): ProductsAdapterViewHolder {
            return ProductsAdapterViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.product_item,
                    parent,
                    false
                ), picasso
            )
        }

    }

}