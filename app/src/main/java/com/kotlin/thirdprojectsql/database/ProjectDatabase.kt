package com.kotlin.thirdprojectsql.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.kotlin.thirdprojectsql.model.task.TaskModel
import com.kotlin.thirdprojectsql.model.user.UserModel

class ProjectDatabase(
    context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val initUserTable = "CREATE TABLE $USER_TABLE " +
                "($COLUMN_PHONE TEXT PRIMARY KEY, " +
                "$COLUMN_EMAIL TEXT, " +
                "$COLUMN_NAME TEXT)"

        val initTaskTable = "CREATE TABLE $TASK_TABLE " +
                "($COLUMN_ID NUMBER PRIMARY KEY, " +
                "$COLUMN_PHONE TEXT, " +
                "$COLUMN_TASK TEXT, " +
                "$COLUMN_COMPLETED TEXT)"

        db.execSQL(initUserTable)
        db.execSQL(initTaskTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun getUser(phone: String): UserModel? {
        val db = readableDatabase
        val query = "SELECT * FROM $USER_TABLE WHERE $COLUMN_PHONE = '$phone'"
        val cursor = db.rawQuery(query, null)
        var user: UserModel? = null

        try {
            if (cursor.moveToFirst()) {
                val phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE))
                val email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))

                user = UserModel(phone, email, name)
            }
        } catch (exception: SQLiteException) {
            Log.d("TAG", "getUser: ${exception.message}")
        }

        return user
    }

    fun insertUser(userModel: UserModel) {
        val db = writableDatabase
        val values = ContentValues()

        values.put(COLUMN_PHONE, userModel.phone)
        values.put(COLUMN_EMAIL, userModel.email)
        values.put(COLUMN_NAME, userModel.name)

        db.insert(USER_TABLE, null, values)
    }

    fun insertTask(taskModel: TaskModel, phone: String) {
        val db = writableDatabase
        val values = ContentValues()

        values.put(COLUMN_PHONE, phone)
        values.put(COLUMN_TASK, taskModel.task)
        values.put(COLUMN_COMPLETED, taskModel.isCompleted.toString())

        db.insert(TASK_TABLE, null, values)
    }

    fun getTasks(phone: String): ArrayList<TaskModel> {
        val db = readableDatabase
        val query = "SELECT * FROM $TASK_TABLE WHERE $COLUMN_PHONE = '$phone'"
        val cursor = db.rawQuery(query, null)
        val tasks = arrayListOf<TaskModel>()

        try {
            while (cursor.moveToNext()){
                val task = cursor.getString(cursor.getColumnIndex(COLUMN_TASK))
                val completed = cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETED))
                val taskModel = TaskModel(task, completed.toBoolean())

                tasks.add(taskModel)
            }
        } catch (exception: SQLiteException) {
            Log.d("TAG", "getTasks: ${exception.message}")
        }

        return tasks
    }

    private companion object {
        // database info
        const val DATABASE_NAME = "project_database"
        const val DATABASE_VERSION = 1

        // user table info
        const val USER_TABLE = "user_table"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_NAME = "name"

        // task table info
        const val TASK_TABLE = "task_table"
        const val COLUMN_ID = "id"
        const val COLUMN_TASK = "task"
        const val COLUMN_COMPLETED = "isCompleted"

        // common
        const val COLUMN_PHONE = "phone"
    }
}