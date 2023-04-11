package com.example.android.training.data

import android.content.Context
import androidx.room.*
import com.example.android.training.presenter.ui.home.model.HomeProductLayout
import com.example.android.training.presenter.ui.home.presenter.dao.ProductDataDao
import com.example.android.training.presenter.ui.product.model.ListConverter
import com.example.android.training.presenter.ui.product.model.ProductLayout

@Database(entities = [HomeProductLayout::class, ProductLayout::class], version = DB_VERSION, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class ProductDetailDatabase : RoomDatabase() {
    abstract fun productDataDao(): ProductDataDao

    companion object {
        @Volatile
        private var databaseInstance: ProductDetailDatabase? = null
        fun getDatabaseInstance(context: Context): ProductDetailDatabase {
            return databaseInstance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, ProductDetailDatabase::class.java, "item_product"
                ).fallbackToDestructiveMigration().build()
                databaseInstance = instance
                return instance
            }
        }

    }
}

const val DB_VERSION = 5