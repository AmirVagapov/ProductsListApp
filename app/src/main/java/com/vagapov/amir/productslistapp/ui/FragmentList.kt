package com.vagapov.amir.productslistapp.ui

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vagapov.amir.productslistapp.R
import com.vagapov.amir.productslistapp.adapter.ProductListAdapter
import com.vagapov.amir.productslistapp.database.ProductDb
import com.vagapov.amir.productslistapp.model.Product
import com.vagapov.amir.productslistapp.ui.interfaces.FragmentCoordinator
import com.vagapov.amir.productslistapp.ui.interfaces.FragmentListRefreshList
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class FragmentList : Fragment(), FragmentListRefreshList{

    private lateinit var coordinator: FragmentCoordinator

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is FragmentCoordinator){
            coordinator = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)
        view.productList.layoutManager = GridLayoutManager(context, 2)
        checkOrientation {
            view.productList.layoutManager = GridLayoutManager(context, 3)
        }

        view.fab.setOnClickListener {
            coordinator.createFragment(FragmentDescription.newInstance(null))
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        loadProducts()
    }

    override fun refreshList() {
        loadProducts()
    }

    private inline fun checkOrientation(f: () -> Unit){
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            f()
        }
    }

    private fun loadProducts() = doAsync {
            val list = ProductDb.database.requestProductList()
            uiThread {
                val adapter = ProductListAdapter(list) {
                    showFragmentDialog(it)
                }
                productList.adapter = adapter
            }
        }

    private fun showFragmentDialog(it: Product) {
        val dialog = FragmentDialog.newInstance(it.id)
        dialog.setTargetFragment(this@FragmentList, FRAGMENT_DIALOG_REQUEST_CODE)
        dialog.show(fragmentManager, null)
    }


    companion object {
        val FRAGMENT_DIALOG_REQUEST_CODE = 1
        fun newInstance() = FragmentList()
    }
}