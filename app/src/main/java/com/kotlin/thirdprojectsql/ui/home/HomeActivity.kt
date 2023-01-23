package com.kotlin.thirdprojectsql.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kotlin.thirdprojectsql.R
import com.kotlin.thirdprojectsql.database.ProjectDatabase
import com.kotlin.thirdprojectsql.ui.add.AddActivity

class HomeActivity : AppCompatActivity() {

    private val database = ProjectDatabase(this)
    private lateinit var phone: String
    private lateinit var recycler: RecyclerView
    private lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()

        addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)

            intent.putExtra("phone", phone)
            startActivity(intent)
        }
    }

    private fun init() {
        phone = intent.getStringExtra("phone")!!
        recycler = findViewById(R.id.recycler)
        addButton = findViewById(R.id.add)
    }

    override fun onResume() {
        super.onResume()
        val tasks = database.getTasks(phone)
        val adapter = HomeAdapter(tasks)

        recycler.adapter = adapter
    }
}