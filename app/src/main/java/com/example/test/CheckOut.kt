package com.example.test

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import android.content.Intent

class CheckOut : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_check_out)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // retreives and prints the total
        val total = intent.getDoubleExtra("total", 0.00)
        val totalTxt = findViewById<TextView>(R.id.totalField)
        totalTxt.text = "₱${"%.2f".format(total)}"

        val paymentField = findViewById<EditText>(R.id.pymntField)
        val changeTxt = findViewById<TextView>(R.id.changeField)

        val finishBtn = findViewById<Button>(R.id.finishBtn)

        finishBtn.setOnClickListener {
            val paymentTxt = paymentField.text.toString()
            if (paymentTxt.isNotEmpty()) {
                val payment = paymentTxt.toDouble()
                if (payment < total) {
                    Toast.makeText(this, "Please enter a valid amount!",Toast.LENGTH_LONG).show()
                } else if (payment != null ) {
                    val change = payment - total
                    changeTxt.text = "₱${"%.2f".format(change)}"

                    Toast.makeText(this, "Order finished!",Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Please enter a valid number!",Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Please enter your payment",Toast.LENGTH_LONG).show()
            }
        }

        val backBtn = findViewById< ImageButton>(R.id.backBtn)

        backBtn.setOnClickListener {
            val cartPage = Intent(this, Cart::class.java)
            startActivity(cartPage)
        }

    }
}