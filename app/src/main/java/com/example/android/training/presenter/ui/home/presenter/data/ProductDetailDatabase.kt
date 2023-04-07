package com.example.android.training.presenter.ui.home.presenter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.training.presenter.ui.home.model.HomeProductLayout
import com.example.android.training.presenter.ui.home.presenter.dao.ProductDataDao

@Database(entities = [HomeProductLayout::class], version = DB_VERSION, exportSchema = false)
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

const val DB_VERSION = 4