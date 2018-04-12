package com.vagapov.amir.productslistapp.state

import com.vagapov.amir.productslistapp.model.Product


interface EditableProductState {

    fun edit(product: Product)
}