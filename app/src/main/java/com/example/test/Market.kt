package com.example.test

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Vector

class Market : AppCompatActivity() {
    data class Order(
        val name: String?,
        val qty: Int,
        val pack: Int,
        val price: Double
    )
    val ordersVector = Vector<Order>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_market)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //gets the orders and puts it inside ordersVector
        val classicBundle = intent.getBundleExtra("classicBundle")
        val brookiesBundle = intent.getBundleExtra("brookiesBundle")
        val biscoffBundle = intent.getBundleExtra("BiscoffBundle")
        val cookiesBundle = intent.getBundleExtra("cookiesBundle")

        if (classicBundle != null) {
            val name = classicBundle.getString("name")
            val qty = classicBundle.getInt("qty")
            val pack = classicBundle.getInt("pack")
            val price = classicBundle.getDouble("price")

            val classicOrder = Order(name, qty, pack, price)

            ordersVector.add(classicOrder)
        }

        if (brookiesBundle != null) {
            val name = brookiesBundle.getString("name")
            val qty = brookiesBundle.getInt("qty")
            val pack = brookiesBundle.getInt("pack")
            val price = brookiesBundle.getDouble("price")

            val brookiesOrder = Order(name, qty, pack, price)

            ordersVector.add(brookiesOrder)
        }

        if (biscoffBundle != null) {
            val name = biscoffBundle.getString("name")
            val qty = biscoffBundle.getInt("qty")
            val pack = biscoffBundle.getInt("pack")
            val price = biscoffBundle.getDouble("price")

            val biscoffOrder = Order(name, qty, pack, price)

            ordersVector.add(biscoffOrder)
        }

        if (cookiesBundle != null) {
            val name = cookiesBundle.getString("name")
            val qty = cookiesBundle.getInt("qty")
            val pack = cookiesBundle.getInt("pack")
            val price = cookiesBundle.getDouble("price")

            val cookiesOrder = Order(name, qty, pack, price)

            ordersVector.add(cookiesOrder)
        }
    }
}