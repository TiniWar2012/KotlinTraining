package com.example.android.training.presenter.ui.home.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "ProductData")
@Parcelize
data class HomeProductLayout(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo( name = "TITLE") val title: String,
    @ColumnInfo(name = "PRICE") var price: Double,
    @ColumnInfo(name = "DESCRIPTION") val description: String,
    @ColumnInfo(name = "IMAGE") val image: String,
    var like: Boolean = false
) : Parcelable