package com.example.test

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CookiesOrder : AppCompatActivity() {
    private var quantity = 1
    private var selectedPack = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cookies_order)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnMinus = findViewById<ImageButton>(R.id.minusButton)
        val btnPlus = findViewById<ImageButton>(R.id.addButton)
        val qtyTextView = findViewById<TextView>(R.id.orderCount)
        val choice1 = findViewById<RadioButton>(R.id.choice1)
        val choice2 = findViewById<RadioButton>(R.id.choice2)
        val btnAddToCart = findViewById<Button>(R.id.addToCartBtn)

        qtyTextView.text = quantity.toString()
        choice1.isChecked = true

        btnPlus.setOnClickListener {
            quantity++
            qtyTextView.text = quantity.toString()
        }

        btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                qtyTextView.text = quantity.toString()
            }
            else if (quantity < 1) {
                val message = "Amount unable to go below 1"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }

        choice1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) selectedPack = 1
        }

        choice2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) selectedPack = 5
        }


        btnAddToCart.setOnClickListener {
            //Placeholder muna pangcheck kung nakaorder yung user
            val message = "Added $quantity pack(s) of $selectedPack-piece brownies to cart!"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}
