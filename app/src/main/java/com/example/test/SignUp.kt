package com.example.test

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
import android.widget.RadioGroup
import android.widget.Toast
import android.content.Intent
import android.widget.CalendarView
import android.view.View
import android.widget.TextView

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rawfullname = findViewById<EditText>(R.id.fullname)
        val rawemail = findViewById<EditText>(R.id.email)
        val rawgendergroup = findViewById<RadioGroup>(R.id.genderGroup)
        val rawusername = findViewById<EditText>(R.id.username)
        val rawpassword = findViewById<EditText>(R.id.newPassword)
        val rawconfirmpass = findViewById<EditText>(R.id.confirmPass)
        val showpass = findViewById<CheckBox>(R.id.showpassword)
        val showconfirmpass = findViewById<CheckBox>(R.id.showconfirmpassword)

        val calendarView = findViewById<CalendarView>(R.id.calendar)
        val rawbirthdate = findViewById<TextView>(R.id.birthdate)
        val showDateBtn = findViewById<Button>(R.id.showDate)

        calendarView.visibility = View.GONE

        showDateBtn.setOnClickListener {
            if (calendarView.visibility == View.GONE) {
                calendarView.visibility = View.VISIBLE
            } else {
                calendarView.visibility = View.GONE
            }
            calendarView.requestLayout()
        }

        calendarView.setOnDateChangeListener {view, year, month, dayOfMonth ->
            val selectedDate = String.format("%02d/%02d/%04d", month+1, dayOfMonth, year)
            rawbirthdate.text = selectedDate

            calendarView.visibility = View.GONE
        }

        val submitBtn = findViewById<Button>(R.id.signup)
        val login = findViewById<Button>(R.id.loginPageBtn)

        showpass.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                rawpassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                rawpassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        showconfirmpass.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                rawconfirmpass.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                rawconfirmpass.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        submitBtn.setOnClickListener {
            val fullname = rawfullname.text.toString()
            val email = rawemail.text.toString()
            val username = rawusername.text.toString()
            val password = rawpassword.text.toString()
            val confirmpass = rawconfirmpass.text.toString()
            val birthdate = rawbirthdate.text.toString()

            val checkedId = rawgendergroup.checkedRadioButtonId
            val gender = when (checkedId) {
                R.id.female -> "female"
                R.id.male -> "male"
                else -> "others"
            }

            if (fullname.isEmpty() || email.isEmpty() || gender.isEmpty() || birthdate.isEmpty() || username.isEmpty() || password.isEmpty() || confirmpass.isEmpty()) {
                Toast.makeText(this, "Please fill up all fields!", Toast.LENGTH_LONG).show()
            }
            else if (password != confirmpass) {
                Toast.makeText(this, "Password does not match1", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this, "Sign up successful!", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Full name: $fullname \n Email: $email \n Username: $username \n " +
                        "Password: $password", Toast.LENGTH_LONG).show()
            }
        }

        login.setOnClickListener {
            val loginPage = Intent(this, LogIn::class.java)

            val username = rawusername.text.toString()
            val email = rawemail.text.toString()
            val password = rawconfirmpass.text.toString()

            loginPage.putExtra("username", username)
            loginPage.putExtra("email", email)
            loginPage.putExtra("password", password)

            startActivity(loginPage)
        }
    }
}