package com.example.todolist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: TodoRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(this, CreateToDo::class.java)
            startActivity(intent)

        }
    }

    override fun onResume() {
        super.onResume()
        updateRecycler()
    }

    private fun updateRecycler() {
        val prefs = getSharedPreferences(getString(R.string.SHARED_PREF_NAME), Context.MODE_PRIVATE)
        val todos = prefs.getStringSet(getString(R.string.TODO_STRINGS), setOf())?.toMutableSet()

        layoutManager = LinearLayoutManager(this)
        todo_recycler_view.layoutManager = layoutManager
        if (todos != null) {
            adapter = TodoRecyclerAdapter(todos.toList())
        }
        todo_recycler_view.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_delete_all) {
            val prefs =
                getSharedPreferences(getString(R.string.SHARED_PREF_NAME), Context.MODE_PRIVATE)
            prefs.edit().putString(getString(R.string.TODO_STRINGS), null).apply()
            updateRecycler()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
