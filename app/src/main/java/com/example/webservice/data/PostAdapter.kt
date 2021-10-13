package com.example.webservice.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.webservice.R
import com.example.webservice.model.Post

class PostAdapter(private val posts: List<Post>, private val context: Context) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

        inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(post: Post) {
                val title = itemView.findViewById<TextView>(R.id.tvTitle)
                val body = itemView.findViewById<TextView>(R.id.tvBody)

                title.text = post.title
                body.text = post.body
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.post_cardview, parent, false)

            return PostViewHolder(view)
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            holder.bind(posts[position])
        }

        override fun getItemCount(): Int {
            return posts.size
        }
}