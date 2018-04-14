package com.vagapov.amir.productslistapp.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import com.vagapov.amir.productslistapp.R


fun FragmentActivity.startFragment(fragment: Fragment) : FragmentTransaction =
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)

fun String.editString() : String{
    return when {
        contains(".") -> {
            val arr = split(".")
            String.format("%.2f", "${arr[0]}.${arr[1]}".toFloat())
        }
        else -> this
    }
}

