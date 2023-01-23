package com.kotlin.thirdprojectsql.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kotlin.thirdprojectsql.R
import com.kotlin.thirdprojectsql.database.ProjectDatabase
import com.kotlin.thirdprojectsql.model.user.UserModel
import com.kotlin.thirdprojectsql.ui.home.HomeActivity

class RegisterActivity : AppCompatActivity() {

    private val database = ProjectDatabase(this)
    private lateinit var viewModel: RegisterViewModel
    private lateinit var phoneInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var nameInput: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()

        registerButton.setOnClickListener {
            val phone = phoneInput.text.toString()
            val email = emailInput.text.toString()
            val name = nameInput.text.toString()
            val isPhoneValid = viewModel.isPhoneValid(phone)

            if (isPhoneValid) {
                if (database.getUser(phone) != null) {
                    Toast.makeText(this, "User already exist", Toast.LENGTH_SHORT).show()
                } else {
                    val user = UserModel(phone, email, name)
                    val intent = Intent(this, HomeActivity::class.java)

                    intent.putExtra("phone", phone)
                    database.insertUser(user)
                    startActivity(intent)
                }
            } else Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        phoneInput = findViewById(R.id.phone)
        emailInput = findViewById(R.id.email)
        nameInput = findViewById(R.id.name)
        registerButton = findViewById(R.id.register)
    }
}