package com.kotlin.thirdprojectsql.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kotlin.thirdprojectsql.R
import com.kotlin.thirdprojectsql.database.ProjectDatabase
import com.kotlin.thirdprojectsql.ui.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    private val database = ProjectDatabase(this)
    private lateinit var viewModel: LoginViewModel
    private lateinit var phoneInput: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()

        loginButton.setOnClickListener {
            val phone = phoneInput.text.toString()
            val isPhoneValid = viewModel.isPhoneValid(phone)

            if (isPhoneValid) {
                val user = database.getUser(phone)

                if (user != null) {
                    val intent = Intent(this, HomeActivity::class.java)

                    intent.putExtra("phone", phone)
                    startActivity(intent)
                } else Toast.makeText(
                    this,
                    "User doesn't exist",
                    Toast.LENGTH_SHORT
                ).show()
            } else Toast.makeText(
                this,
                "Please enter a valid number",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        phoneInput = findViewById(R.id.input)
        loginButton = findViewById(R.id.login)
    }
}