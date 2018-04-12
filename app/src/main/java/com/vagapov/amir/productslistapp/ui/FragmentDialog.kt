package com.vagapov.amir.productslistapp.ui

import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vagapov.amir.productslistapp.R
import com.vagapov.amir.productslistapp.database.ProductDb
import com.vagapov.amir.productslistapp.model.Product
import com.vagapov.amir.productslistapp.ui.interfaces.FragmentCoordinator
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class FragmentDialog : DialogFragment() {

    private lateinit var coordinator: FragmentCoordinator

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is FragmentCoordinator){
            coordinator = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val view = inflater.inflate(R.layout.fragment_dialog, container, false)

        val id = arguments.getInt(ARGS_DIALOG)

        view.btnEdit.setOnClickListener {
            coordinator.createFragment(FragmentDescription.newInstance(id))
            dismiss()
        }
        view.btnDelete.setOnClickListener{
            doAsync {
                ProductDb.database.deleteProduct(id)
                uiThread { dismiss() }
            }
        }
        return  view
    }

    companion object {
        private val ARGS_DIALOG = "product_dialog"
        fun newInstance(id: Int) : FragmentDialog{
            val fragment = FragmentDialog()
            val args = Bundle()
            args.putInt(ARGS_DIALOG, id)
            fragment.arguments = args
            return fragment
        }
    }
}