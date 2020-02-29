package com.example.todolist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_item_row.view.*

class TodoRecyclerAdapter(val todos: List<String>)
    : RecyclerView.Adapter<TodoRecyclerAdapter.TodoTextHolder>() {

    class TodoTextHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {
        var view: View = v
        var todo: String = ""

        init {
            v.setOnClickListener(this)
        }

        fun bindTodo(todo: String) {
            this.todo = todo
            view.listItemTextView.text = todo
        }

        override fun onClick(v: View?) {
            val intent = Intent(view.context, CompleteTodoActivity::class.java)
            intent.putExtra("todo", todo)
            startActivity(view.context, intent, null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoTextHolder {
        return TodoTextHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_item_row, parent, false))
    }

    override fun getItemCount(): Int {
        return todos.count()
    }

    override fun onBindViewHolder(holder: TodoTextHolder, position: Int) {
        val todo = todos[position]
        holder.bindTodo(todo)
    }


}