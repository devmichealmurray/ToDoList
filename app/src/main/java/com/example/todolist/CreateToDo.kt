package com.example.todolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_to_do.*

class CreateToDo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_to_do)

        save_button.setOnClickListener {
            var title = ""
            title = if (important_checkbox.isChecked) {
                "\u2757 " + todo_text_view.text.toString().capitalize()
            } else {
                todo_text_view.text.toString().capitalize()
            }

            val prefs = getSharedPreferences(getString(R.string.SHARED_PREF_NAME), Context.MODE_PRIVATE)
            val todos = prefs.getStringSet(getString(R.string.TODO_STRINGS), setOf())?.toMutableSet()
            todos?.add(title)
            prefs.edit().putStringSet(getString(R.string.TODO_STRINGS), todos).apply()
            finish()
        }
    }
}
