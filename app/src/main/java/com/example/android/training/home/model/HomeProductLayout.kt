package com.example.android.training.home.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProductData")
data class HomeProductLayout(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo( name = "TITLE") val title: String,
    @ColumnInfo(name = "PRICE") var price: Double,
    @ColumnInfo(name = "DESCRIPTION") val description: String,
    @ColumnInfo(name = "IMAGE") val image: String,
    @ColumnInfo(name = "LIKE") var like: Boolean = false
)