package com.kotlin.thirdprojectsql.ui.add

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.thirdprojectsql.R
import com.kotlin.thirdprojectsql.database.ProjectDatabase
import com.kotlin.thirdprojectsql.model.task.TaskModel

class AddActivity : AppCompatActivity() {

    private val database = ProjectDatabase(this)
    private lateinit var phone: String
    private lateinit var taskInput: EditText
    private lateinit var completedBox: CheckBox
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        init()

        addButton.setOnClickListener {
            val taskName = taskInput.text.toString()
            val isCompleted = completedBox.isChecked
            val task = TaskModel(taskName, isCompleted)

            database.insertTask(task, phone)

            finish()
        }
    }

    private fun init() {
        phone = intent.getStringExtra("phone")!!
        taskInput = findViewById(R.id.task_name)
        completedBox = findViewById(R.id.check)
        addButton = findViewById(R.id.add)
    }
}