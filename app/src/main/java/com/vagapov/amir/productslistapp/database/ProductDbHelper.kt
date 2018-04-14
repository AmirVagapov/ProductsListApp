package com.vagapov.amir.productslistapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.vagapov.amir.productslistapp.App
import org.jetbrains.anko.db.*


class ProductDbHelper(context: Context = App.instance)
    : ManagedSQLiteOpenHelper(context, ProductDbHelper.DB_NAME, null, ProductDbHelper.DB_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(ProductTable.TABLE_NAME, true,
                ProductTable.ID to INTEGER + PRIMARY_KEY,
                ProductTable.NAME to TEXT,
                ProductTable.PRICE to REAL,
                ProductTable.COUNT to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.dropTable(ProductTable.NAME, true)
        onCreate(db)
    }


    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance by lazy { ProductDbHelper() }
    }
}