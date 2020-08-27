package com.mac.intacttest.activities.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mac.intacttest.activities.data.ProductsCollection
import com.mac.intacttest.activities.viewholder.ProductsAdapterViewHolder
import com.squareup.picasso.Picasso

class ProductsAdapter(private val picasso: Picasso) :
    ListAdapter<ProductsCollection.Article, ProductsAdapterViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductsAdapterViewHolder.create(parent, picasso)

    override fun onBindViewHolder(holderAdapter: ProductsAdapterViewHolder, position: Int) {
        holderAdapter.bind(getItem(position))
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductsCollection.Article>() {

            override fun areItemsTheSame(
                oldItem: ProductsCollection.Article,
                newItem: ProductsCollection.Article
            ) = oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: ProductsCollection.Article,
                newItem: ProductsCollection.Article
            ) = oldItem == newItem
        }
    }

}
