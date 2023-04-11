package com.example.android.training.presenter.ui.home.presenter.dao

import androidx.room.*
import com.example.android.training.presenter.ui.home.model.HomeProductLayout
import com.example.android.training.presenter.ui.product.model.ProductLayout
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ProductDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun likeProductData(vararg product: HomeProductLayout): Completable

    @Query("DELETE FROM ProductData WHERE TITLE LIKE :title")
    fun unlikeProduct(title: String): Completable

    @Query("SELECT * FROM ProductData WHERE TITLE LIKE :name")
    fun getAllProduct(name: String): Single<List<HomeProductLayout>>

    @Query("SELECT * FROM NewProductData WHERE id LIKE :id")
    fun getProduct(id: Int): Single<List<ProductLayout>>

    @Update
    fun updateProductDataRx(vararg product: HomeProductLayout): Completable

    @Query("DELETE FROM ProductData")
    fun deleteProduct(): Completable
}