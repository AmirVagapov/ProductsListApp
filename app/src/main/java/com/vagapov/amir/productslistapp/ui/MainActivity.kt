package com.vagapov.amir.productslistapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.vagapov.amir.productslistapp.R
import com.vagapov.amir.productslistapp.extensions.startFragment
import com.vagapov.amir.productslistapp.model.Product
import com.vagapov.amir.productslistapp.ui.interfaces.FragmentCoordinator

class MainActivity : AppCompatActivity(), FragmentCoordinator {


    override fun createFragment(fragment: Fragment) {
        startFragment(fragment)
                .addToBackStack(null)
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if(fragment == null) {
            startFragment(FragmentList.newInstance()).commit()
        }
    }


     override fun createDialogFragment(id: Int) {
        FragmentDialog.newInstance(id).show(fragmentManager, null)
    }
}
