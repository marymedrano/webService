package com.example.webservice.data

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.webservice.PostActivity
import com.example.webservice.R
import com.example.webservice.model.User

class UsersAdapter(private val users: List<User>, private val context: Context) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private var listener: listenerUser? = null

    interface listenerUser {
        fun onClickListener(user: User)
    }

    fun setListenerUser(listener: listenerUser) {
        this.listener = listener
    }

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            val name = itemView.findViewById<TextView>(R.id.tvName)
            val email = itemView.findViewById<TextView>(R.id.tvEmail)

            name.text = user.name
            email.text = user.email

            itemView.setOnClickListener {
                listener?.onClickListener(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_cardview, parent, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }
}