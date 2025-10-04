package com.example.test

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rawemail = findViewById<EditText>(R.id.email)
        val rawpassword = findViewById<EditText>(R.id.newPassword)
        val showpass = findViewById<CheckBox>(R.id.showpassword)

        val username = intent.getStringExtra("username")
        val signupemail = intent.getStringExtra("email")
        val confirmpass = intent.getStringExtra("password")

        val submitBtn = findViewById<Button>(R.id.signup)
        val signup = findViewById<Button>(R.id.signupPageBtn)

        showpass.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                rawpassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                rawpassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        submitBtn.setOnClickListener {
            val email = rawemail.text.toString()
            val password = rawpassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill up all fields!", Toast.LENGTH_LONG).show()
            }
            else if (email != signupemail) {
                Toast.makeText(this, "Email does not exist!", Toast.LENGTH_LONG).show()
            }
            else if (password != confirmpass) {
                Toast.makeText(this, "Invalid password!", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this, "Log in successful!", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Hello, $username!", Toast.LENGTH_LONG).show()

                val dashboardPage = Intent(this, homepage::class.java)
                startActivity(dashboardPage)
            }
        }

        signup.setOnClickListener {
            val signupPage = Intent(this, SignUp::class.java)
            startActivity(signupPage)
        }
    }
}