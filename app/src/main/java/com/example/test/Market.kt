package com.example.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Vector

class Market : AppCompatActivity() {
    data class Order(
        val name: String?,
        var qty: Int,
        val pack: Int,
        val unitPrice: Double,
        val img: String
    ) : java.io.Serializable {
        val price: Double
            get() = qty * unitPrice
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_market)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val cartPage = Intent(this, Cart::class.java)

        //gets the orders and puts it inside ordersVector
        val classicBundle = intent.getBundleExtra("classicBundle")
        val brookiesBundle = intent.getBundleExtra("brookiesBundle")
        val biscoffBundle = intent.getBundleExtra("BiscoffBundle")
        val cookiesBundle = intent.getBundleExtra("cookiesBundle")

        if (classicBundle != null) {
            val name = classicBundle.getString("name")
            var qty = classicBundle.getInt("qty")
            val pack = classicBundle.getInt("pack")
            val unitPrice = classicBundle.getDouble("unitPrice")

            val classicOrder = Order(name, qty, pack, unitPrice, "img_brownies")

            OrderManager.orders.add(classicOrder)
        }

        if (brookiesBundle != null) {
            val name = brookiesBundle.getString("name")
            var qty = brookiesBundle.getInt("qty")
            val pack = brookiesBundle.getInt("pack")
            val unitPrice = brookiesBundle.getDouble("unitPrice")

            val brookiesOrder = Order(name, qty, pack, unitPrice, "img_brookies")

            OrderManager.orders.add(brookiesOrder)
        }

        if (biscoffBundle != null) {
            val name = biscoffBundle.getString("name")
            var qty = biscoffBundle.getInt("qty")
            val pack = biscoffBundle.getInt("pack")
            val unitPrice = biscoffBundle.getDouble("unitPrice")

            val biscoffOrder = Order(name, qty, pack, unitPrice, "img_biscoff")

            OrderManager.orders.add(biscoffOrder)
        }

        if (cookiesBundle != null) {
            val name = cookiesBundle.getString("name")
            var qty = cookiesBundle.getInt("qty")
            val pack = cookiesBundle.getInt("pack")
            val unitPrice = cookiesBundle.getDouble("unitPrice")

            val cookiesOrder = Order(name, qty, pack, unitPrice, "img_cookies")

            OrderManager.orders.add(cookiesOrder)
        }

        cartPage.putExtra("orderList", ArrayList(OrderManager.orders))
        Log.d("MarketDebug", "Orders count: ${OrderManager.orders.size}")
        Toast.makeText(this, "Orders: ${OrderManager.orders.size}", Toast.LENGTH_SHORT).show()

        val homepageButton = findViewById<Button>(R.id.return_dashboard)
        val cartButton = findViewById<Button>(R.id.btnCart)

        homepageButton.setOnClickListener {
            val homepageButton = Intent(this, homepage::class.java)
            startActivity(homepageButton)
        }

        cartButton.setOnClickListener {
            val cartButton = Intent(this, Cart::class.java)
            startActivity(cartButton)
        }

        //Transferring to pages of respective items
        //Variable declarations
        val browniesTransfer = findViewById<LinearLayout>(R.id.item_brownies)
        val brookiesTransfer = findViewById<LinearLayout>(R.id.item_brookies)
        val biscoffTransfer = findViewById<LinearLayout>(R.id.item_biscoff)
        val cookiesTransfer = findViewById<LinearLayout>(R.id.item_cookies)

        // brownies
        browniesTransfer.setOnClickListener {
            val browniesTransfer = Intent(this, BrowniesOrder::class.java)
            startActivity(browniesTransfer)
        }

        // brookies
        brookiesTransfer.setOnClickListener {
            val brookiesTransfer = Intent(this, BrookiesOrder::class.java)
            startActivity(brookiesTransfer)
        }

        // biscoff
        biscoffTransfer.setOnClickListener {
            val biscoffTransfer = Intent(this, BiscoffOrder::class.java)
            startActivity(biscoffTransfer)
        }

        // cookies
        cookiesTransfer.setOnClickListener {
            val cookiesTransfer = Intent(this, CookiesOrder::class.java)
            startActivity(cookiesTransfer)
        }
    }
}