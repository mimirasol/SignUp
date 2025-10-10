package com.example.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Cart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerCart)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get the list of orders from the global OrderManager
        val orderList = OrderManager.orders

        // Pass the list to the adapter
        Log.d("CartDebug", "Orders count in Cart: ${OrderManager.orders.size}")
        recyclerView.adapter = OrderAdapter(OrderManager.orders)

        // Add 16dp of space between items
        val spacingInDp = 16
        val scale = resources.displayMetrics.density
        val spacingInPixels = (spacingInDp * scale + 0.5f).toInt()

        recyclerView.addItemDecoration(SpacingOrders(spacingInPixels))

        val marketButton = findViewById<ImageButton>(R.id.backBtn)

        marketButton.setOnClickListener {
            val marketButton = Intent(this, Market::class.java)
            startActivity(marketButton)
        }
    }
}
