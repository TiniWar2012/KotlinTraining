package com.example.android.training.presenter.ui.cart.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.training.presenter.ui.product.model.ProductLayout
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Entity(tableName = "CartData")
@Parcelize
data class CartLayout(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = "brand") val brand: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "discountPercentage") val discountPercentage: Double,
    @ColumnInfo(name = "images") val images: List<String>,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "rating") val rating: Double,
    @ColumnInfo(name = "stock") val stock: Int,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "quantity") var quantity: Int,
) : Parcelable

object CartManager {
    private var cartItems = mutableListOf<CartLayout>()

    fun addItemToCart(
        productLayout: ProductLayout
    ) {
        val quantity = 1
        val existingItem = cartItems.find { it.id == productLayout.id.toString() }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            with(productLayout) {
                cartItems.add(CartLayout(
                    id.toString(),
                    brand,
                    category,
                    description,
                    discountPercentage,
                    images,
                    price,
                    rating,
                    stock,
                    thumbnail,
                    title,
                    quantity
                ))
            }
        }
    }

    fun updateItemQuantity(itemId: String, newQuantity: Int) {
        val item = cartItems.find { it.id == itemId }
        if (item != null) {
            item.quantity = newQuantity
        }
    }

    fun removeItemFromCart(itemId: String) {
        val item = cartItems.find { it.id == itemId }
        cartItems.remove(item)
    }

    fun clearCart() {
        cartItems.clear()
    }

    fun getCartItems(): MutableList<CartLayout> {
        return cartItems
    }
}
