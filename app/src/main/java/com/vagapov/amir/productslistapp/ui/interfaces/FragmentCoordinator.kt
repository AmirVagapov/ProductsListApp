package com.vagapov.amir.productslistapp.ui.interfaces

import android.support.v4.app.Fragment
import com.vagapov.amir.productslistapp.model.Product

interface FragmentCoordinator {

    fun createFragment(fragment: Fragment)

    fun createDialogFragment(id: Int)


}