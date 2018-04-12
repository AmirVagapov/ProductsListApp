package com.vagapov.amir.productslistapp.model

import java.io.Serializable

data class Product(val id: Int = 1,
                   var name: String = "",
                   var price: Float = 0f,
                   var count: Int = 0)
    : Serializable