package com.example.android.training.base

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.example.android.training.R

class ButtonAddToCartView(context: Context, attributeSet: AttributeSet) : FrameLayout(context, attributeSet) {

    private var limit = 0

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

    fun setLimit(limit: Int) {
        this.limit = limit
    }
}