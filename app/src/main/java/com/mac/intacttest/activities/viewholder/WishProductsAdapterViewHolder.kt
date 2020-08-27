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

class WishProductsAdapterViewHolder constructor(
    override val containerView: View, private val picasso: Picasso
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(product: ProductsCollection.Article) {
        picasso.load(product.urlToImage)
            .fit()
            .centerCrop()
            .into(containerView.findViewById<ImageView>(R.id.imageViewWish))
        containerView.findViewById<TextView>(R.id.tvName).text = product.title

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
            }
            val context: MainActivity = v.context as MainActivity
            context.startActivityForResult(intent, MainActivity.ACTION_REMOVE)

        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            picasso: Picasso
        ): WishProductsAdapterViewHolder {
            return WishProductsAdapterViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_wish_list,
                    parent,
                    false
                ), picasso
            )
        }

    }
}