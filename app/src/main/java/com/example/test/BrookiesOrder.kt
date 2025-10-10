package com.example.test

import android.content.Intent
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


class BrookiesOrder : AppCompatActivity() {
    private var quantity = 1
    private var selectedPack = 3
    private var selectedPrice = 52.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_brookies_order)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnMinus = findViewById<ImageButton>(R.id.minusButton)
        val btnPlus = findViewById<ImageButton>(R.id.addButton)
        val qtyTextView = findViewById<TextView>(R.id.orderCount)   // renamed to avoid shadowing
        val choice1 = findViewById<RadioButton>(R.id.choice1)
        val choice2 = findViewById<RadioButton>(R.id.choice2)
        val btnAddToCart = findViewById<Button>(R.id.addToCartBtn)
        val btnBack = findViewById<ImageButton>(R.id.backButton)

        btnBack.setOnClickListener {
            val marketPage = Intent(this, Market::class.java)
            startActivity(marketPage)
        }

        qtyTextView.text = quantity.toString()
        choice1.isChecked = true

        btnPlus.setOnClickListener {
            if (quantity < 10) {
                quantity++
                qtyTextView.text = quantity.toString()
            }
            else {
                val message = "Max order quantity is 10!"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }

        }

        btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                qtyTextView.text = quantity.toString()
            }
            else if (quantity < 1) {
                val message = "Amount unable to go below 1!"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }

        }

        choice1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) selectedPack = 3
                           selectedPrice = 52.00
        }

        choice2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) selectedPack = 9
                           selectedPrice = 155.00
        }


        btnAddToCart.setOnClickListener {
            //Placeholder muna pangcheck kung nakaorder yung user
            val message = "Added $quantity pack(s) of $selectedPack-piece brownies to cart!"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            val initialPrice = quantity * selectedPrice
            Toast.makeText(this, "$initialPrice", Toast.LENGTH_SHORT).show()

            val productName = "Brookies"
            val marketPage = Intent(this, Market::class.java)

            val brookiesBundle = Bundle().apply {
                putString("name", productName)
                putInt("qty", quantity)
                putInt("pack", selectedPack)
                putDouble("price", initialPrice)
            }

            marketPage.putExtra("brookiesBundle", brookiesBundle)
        }
    }
}
