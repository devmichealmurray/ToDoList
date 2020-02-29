package com.example.todolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_complete_todo.*

class CompleteTodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_todo)

        val todo = intent.extras?.getString("todo")
        toDoTextView.text = todo

        task_completed.setOnClickListener {
            val prefs = getSharedPreferences(getString(R.string.SHARED_PREF_NAME), Context.MODE_PRIVATE)
            val todos = prefs.getStringSet(getString(R.string.TODO_STRINGS), setOf())?.toMutableSet()

            todos?.remove(todo)
            prefs.edit().putStringSet(getString(R.string.TODO_STRINGS), todos).apply()
            finish()
        }
    }
}
