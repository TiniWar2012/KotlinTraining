package com.example.android.training.home.presenter.dao

import androidx.room.*
import com.example.android.training.home.model.HomeProductLayout
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ProductDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductData(vararg product: HomeProductLayout ) : Completable

    @Query("SELECT * FROM ProductData")
    fun getAllProduct() :   Single<List<HomeProductLayout>>

    @Delete
    fun deleteProductData (vararg product: HomeProductLayout): Completable

    @Update
    suspend fun updateProductData (vararg product: HomeProductLayout )

    @Insert
    fun updateProductDataRx (vararg product: HomeProductLayout ): Completable
}