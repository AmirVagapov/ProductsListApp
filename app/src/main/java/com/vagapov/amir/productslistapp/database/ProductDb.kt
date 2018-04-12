package com.vagapov.amir.productslistapp.database

import com.vagapov.amir.productslistapp.model.Product
import org.jetbrains.anko.db.*

class ProductDb(private val productDbHelper: ProductDbHelper = ProductDbHelper.instance) {


    fun requestProduct(id: Int): Product = productDbHelper.use {
        select(ProductTable.TABLE_NAME)
                .whereSimple("id = ?", id.toString())
                .parseSingle(classParser())
    }


    fun requestProductList(): List<Product> = productDbHelper.use {
        select(ProductTable.TABLE_NAME).parseList(classParser())
    }

    fun insertProduct(product: Product) = productDbHelper.use {
        with(product) {
            insert(ProductTable.TABLE_NAME,
                    ProductTable.NAME to name,
                    ProductTable.COUNT to count,
                    ProductTable.PRICE to price)
        }
    }


    fun updateProduct(product: Product) = productDbHelper.use {
        with(product) {
            update(ProductTable.TABLE_NAME,
                    ProductTable.NAME to name,
                    ProductTable.COUNT to count,
                    ProductTable.PRICE to price)
                    .whereSimple("id = $id")
                    .exec()
        }
    }

    fun deleteProduct(id: Int) = productDbHelper.use {
        delete(ProductTable.TABLE_NAME, "id = $id")
    }

    companion object {
        val database by lazy { ProductDb() }
    }
}