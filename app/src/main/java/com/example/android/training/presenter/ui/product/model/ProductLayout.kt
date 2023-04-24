package com.example.android.training.presenter.ui.product.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Entity(tableName = "NewProductData")
@Parcelize
data class ProductLayout(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "brand") val brand: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "discountPercentage") var discountPercentage: Double,
    @ColumnInfo(name = "images") val images: List<String>,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "rating") val rating: Double,
    @ColumnInfo(name = "stock") val stock: Int,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "title") val title: String
) : Parcelable

class ListConverter {

    @TypeConverter
    fun fromString(value: String?): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String?>): String {
        return Gson().toJson(list)
    }
}