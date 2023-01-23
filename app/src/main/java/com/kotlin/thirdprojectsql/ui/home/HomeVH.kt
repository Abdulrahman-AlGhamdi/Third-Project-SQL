package com.kotlin.thirdprojectsql.ui.home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.thirdprojectsql.R
import com.kotlin.thirdprojectsql.model.task.TaskModel

class HomeVH(view: View) : RecyclerView.ViewHolder(view) {

    private val taskText = view.findViewById<TextView>(R.id.task)
    private val completedText = view.findViewById<TextView>(R.id.completed)

    fun bind(task: TaskModel) {
        taskText.text = task.task

        if (task.isCompleted) completedText.text = "Completed"
        else completedText.text = "Not Completed"
    }
}