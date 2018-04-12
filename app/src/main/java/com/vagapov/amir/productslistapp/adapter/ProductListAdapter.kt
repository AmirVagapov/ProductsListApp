package com.vagapov.amir.productslistapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vagapov.amir.productslistapp.R
import com.vagapov.amir.productslistapp.model.Product
import kotlinx.android.synthetic.main.item_product.view.*


class ProductListAdapter(private val productList: List<Product> = ArrayList(),
                         private val itemClick: (Product) -> Unit) :
        RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater
                .from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view, itemClick)
    }

    override fun getItemCount() = productList.size


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
            holder.bindProduct(productList[position])



    class ProductViewHolder(private val view: View, private val itemClick: (Product) -> Unit) :
            RecyclerView.ViewHolder(view) {


        fun bindProduct(product: Product) = with(product) {
            view.item_name.text = product.name
            view.item_count.text = view.context.getString(R.string.countItem, count.toString())
            view.item_price.text = view.context.getString(R.string.priceItem, price.toString())
            view.setOnClickListener { itemClick(this) }
        }

    }
}