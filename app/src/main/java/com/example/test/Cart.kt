package com.example.test

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Cart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart)

        // Receive the vector from Market
        val ordersVector = intent.getSerializableExtra("ordersList") as? java.util.Vector<Market.Order>

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerCart)
        recyclerView.layoutManager = LinearLayoutManager(this)

        if (ordersVector != null) {
            recyclerView.adapter = OrderAdapter(this, ordersVector.toList())
        }
    }
}
