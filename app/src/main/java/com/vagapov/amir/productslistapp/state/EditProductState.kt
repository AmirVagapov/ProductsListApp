package com.vagapov.amir.productslistapp.state

import com.vagapov.amir.productslistapp.database.ProductDb
import com.vagapov.amir.productslistapp.model.Product


class EditProductState : EditableProductState {

    override fun edit(product: Product) {
        ProductDb.database.updateProduct(product)
    }


}