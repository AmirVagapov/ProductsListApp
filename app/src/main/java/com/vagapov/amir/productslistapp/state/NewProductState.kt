package com.vagapov.amir.productslistapp.state

import com.vagapov.amir.productslistapp.database.ProductDb
import com.vagapov.amir.productslistapp.model.Product


class NewProductState : EditableProductState {

    override fun edit(product: Product) {
        ProductDb.database.insertProduct(product)
    }
}