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

    private var limit = 0
    private lateinit var listener: HandleQuantity


    fun setListener(listener: HandleQuantity) {
        this.listener = listener
    }
    init {
        inflate(context, R.layout.item_cart_counter, this)

        findViewById<TextView>(R.id.decrement_button).setOnClickListener {
            if (findViewById<TextView>(R.id.counter_textview).text.toString().toInt() == limit) {

            } else {

            }
        }
    }

    fun setSomething() {
        findViewById<TextView>(R.id.counter_textview)
    }

    fun handleQuantity() {
        findViewById<TextView>(R.id.counter_textview).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newQuantity = s.toString().toIntOrNull() ?: 0
                listener.onQuantityChanged(newQuantity)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    fun setLimit(limit: Int) {
        this.limit = limit
    }
}

interface HandleQuantity {
    fun onQuantityChanged(quantity: Int)
}
