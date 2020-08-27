package com.mac.intacttest.activities.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mac.intacttest.activities.data.ProductsCollection
import com.mac.intacttest.activities.viewholder.WishProductsAdapterViewHolder
import com.squareup.picasso.Picasso

class WishProductAdapter(private val picasso: Picasso) :
    RecyclerView.Adapter<WishProductsAdapterViewHolder>()
/*ListAdapter<ProductsCollection.Article, WishProductsAdapterViewHolder>(
    //DIFF_CALLBACK
) */ {

    val items: ArrayList<ProductsCollection.Article> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WishProductsAdapterViewHolder.create(parent, picasso)

    override fun onBindViewHolder(holderAdapter: WishProductsAdapterViewHolder, position: Int) {
        holderAdapter.bind(items.get(position))
    }

    public fun updateData(itemsList: ArrayList<ProductsCollection.Article>) {
        items.clear()
        items.addAll(itemsList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    /*companion object {

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
    }*/

}