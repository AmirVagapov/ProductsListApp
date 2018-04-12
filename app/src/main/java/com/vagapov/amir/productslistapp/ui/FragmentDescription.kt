package com.vagapov.amir.productslistapp.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vagapov.amir.productslistapp.state.EditProductState
import com.vagapov.amir.productslistapp.state.NewProductState
import com.vagapov.amir.productslistapp.R
import com.vagapov.amir.productslistapp.database.ProductDb
import com.vagapov.amir.productslistapp.extensions.editString
import com.vagapov.amir.productslistapp.model.Product
import com.vagapov.amir.productslistapp.state.EditableProductState
import kotlinx.android.synthetic.main.fragment_description.*
import kotlinx.android.synthetic.main.fragment_description.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread


class FragmentDescription : Fragment() {

    private lateinit var state: EditableProductState
    private var productId : Int = DEFAULT_ID

    private fun getProductFromArgs(view: View) {
        productId = arguments.getInt(ARGS_PRODUCT)
        if(productId != DEFAULT_ID){
            state = EditProductState()
            doAsync {
                val product = ProductDb.database.requestProduct(productId)
                uiThread { fillDescription(product, view) }
            }
        } else {
            state = NewProductState()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_description, container, false)
        getProductFromArgs(view)
        return view
    }

    private fun fillDescription(product: Product, view: View) {
        with(product) {
            view.etName.setText(name)
            view.etCount.setText(count.toString())
            view.etPrice.setText(price.toString())
        }
    }

    override fun onPause() {
        super.onPause()
        if(checkOnNull()) state.edit(createProduct()) else
            context.toast(getString(R.string.error_create_product))
    }

    private fun createProduct(): Product {
        val name = etName.text.toString()
        val count = etCount.text.toString().toInt()
        val price = etPrice.text.toString().editString().toFloat()
        return Product(productId, name = name, price = price, count = count)
    }

    private fun checkOnNull() = !(etCount.text.isEmpty() ||
            etPrice.text.isEmpty() ||
            etName.text.isEmpty())


    companion object {
        private val DEFAULT_ID = -1

        private val ARGS_PRODUCT = "product"

        fun newInstance(id: Int?): FragmentDescription {
            val fragment = FragmentDescription()
            val args = Bundle()
            if(id == null) args.putInt(ARGS_PRODUCT, DEFAULT_ID) else args.putInt(ARGS_PRODUCT, id)
            fragment.arguments = args
            return fragment
        }
    }
}