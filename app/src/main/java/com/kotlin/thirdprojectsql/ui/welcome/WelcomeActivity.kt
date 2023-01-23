package com.kotlin.thirdprojectsql.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.thirdprojectsql.R
import com.kotlin.thirdprojectsql.ui.login.LoginActivity
import com.kotlin.thirdprojectsql.ui.register.RegisterActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        init()

        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun init() {
        loginButton = findViewById(R.id.login)
        registerButton = findViewById(R.id.register)
    }
}