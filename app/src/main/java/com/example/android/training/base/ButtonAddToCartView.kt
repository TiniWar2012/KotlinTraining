package com.example.android.training.base

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.example.android.training.R

class ButtonAddToCartView(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet) {

    private var quantity = 1
    private var minQuantity = 0
    private var maxQuantity = 1
    private val quantityTextView: TextView
    private val increaseButton: TextView
    private val descreaseButton: TextView
    private lateinit var listener: HandleQuantity


    fun setListener(listener: HandleQuantity) {
        this.listener = listener
    }

    init {
        inflate(context, R.layout.item_cart_counter, this)
        quantityTextView = findViewById(R.id.counter_textview)
        increaseButton = findViewById(R.id.increment_button)
        descreaseButton = findViewById(R.id.decrement_button)

        increaseButton.setOnClickListener {
            increaseQuantity()
        }

        descreaseButton.setOnClickListener {
            decreaseQuantity()
        }
    }
    private fun increaseQuantity() {
        if (quantity < maxQuantity) {
            quantity++
            quantityTextView.text = quantity.toString()
        }
    }

    private fun decreaseQuantity() {
        if (quantity > minQuantity) {
            quantity--
            quantityTextView.text = quantity.toString()
        }
    }


//    fun handleQuantity() {
//        findViewById<TextView>(R.id.counter_textview).addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(
//                s: CharSequence?, start: Int, count: Int, after: Int
//            ) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                val newQuantity = s.toString().toIntOrNull() ?: 0
//                listener.onQuantityChanged(newQuantity)
//            }
//
//            override fun afterTextChanged(s: Editable?) {}
//        })
//    }

    fun setMaxQuantity(max: Int) {
        maxQuantity = max
        if (quantity > maxQuantity) {
            quantity = maxQuantity
            quantityTextView.text = quantity.toString()
        }
    }

    fun setMinQuantity(min: Int) {
        minQuantity = min
        if (quantity < minQuantity) {
            quantity = minQuantity
            quantityTextView.text = quantity.toString()
        }
    }

     fun  setQuantity(qty: Int) {
        quantity = when {
            qty > maxQuantity -> maxQuantity
            qty < minQuantity -> minQuantity
            else -> qty
        }
        quantityTextView.text = quantity.toString()
    }

    fun getQuantity(): Int {
        return quantity
    }
}

interface HandleQuantity {
    fun setQuantity(quantity: Int)
}
