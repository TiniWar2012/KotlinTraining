package com.example.android.training.home.presenter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.training.home.model.HomeProductLayout
import com.example.android.training.home.presenter.dao.ProductDataDao

@Database(entities = [HomeProductLayout::class], version = DB_VERSION, exportSchema = false)
abstract class ProductDetailDatabase : RoomDatabase() {
    abstract fun productDataDao() : ProductDataDao
    companion object{
        @Volatile
        private var databseInstance : ProductDetailDatabase?= null
        fun getDatabasenIstance(context: Context): ProductDetailDatabase {
            return databseInstance ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDetailDatabase::class.java,
                    "item_product"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                databseInstance = instance
                return instance
            }
        }

    }
}
const val DB_VERSION = 1