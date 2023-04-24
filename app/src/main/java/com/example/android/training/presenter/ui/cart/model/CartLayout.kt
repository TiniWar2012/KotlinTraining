package com.example.android.training.presenter.ui.cart.model

import android.os.Parcelable
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.training.presenter.ui.product.model.ProductLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Entity(tableName = "CartData")
@Parcelize
data class CartLayout(
    @PrimaryKey(autoGenerate = false) val id: String = "",
    @ColumnInfo(name = "brand") val brand: String = "",
    @ColumnInfo(name = "category") val category: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "discountPercentage") val discountPercentage: Double = 0.0,
    @ColumnInfo(name = "images") val images: List<String> = emptyList(),
    @ColumnInfo(name = "price") val price: Int = 0,
    @ColumnInfo(name = "rating") val rating: Double = 0.0,
    @ColumnInfo(name = "stock") val stock: Int = 0,
    @ColumnInfo(name = "thumbnail") val thumbnail: String = "",
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "quantity") var quantity: Int = 0,
) : Parcelable {

}

object CartManager {
    private var cartItems = mutableListOf<CartLayout>()

    fun addItemToCart(
        productLayout: ProductLayout
    ) {
        val cartRef = Firebase.firestore.collection("Cart")
        val quantity = 1
        val existingItem = cartItems.find { it.id == productLayout.id.toString() }
        if (existingItem != null) {
            existingItem.quantity++
//            cartRef.document(existingItem.toString())
//                .update("quantity",quantity++)
        } else {
            with(productLayout) {
                cartItems.add(
                    CartLayout(
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
                    )
                )
                cartRef.add(
                    CartLayout(
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
                    )
                ).addOnSuccessListener {
                    Log.d("--->", "Success")
                }.addOnFailureListener {
                    Log.d("--->", "${it.message}")
                }
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

    fun getCartItems(): MutableList<CartLayout> {
        return cartItems
    }
}
