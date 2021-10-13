package com.example.webservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webservice.data.PostAdapter
import com.example.webservice.dto.PostResponse
import com.example.webservice.model.Post
import com.example.webservice.model.User
import com.example.webservice.network.ApiAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.Exception

class PostActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var postList : List<Post>
    private var layoutManager: RecyclerView.LayoutManager?= null
    private var postAdapter: PostAdapter? = null
    private lateinit var progressBar: ProgressBar
    private lateinit var tvNoData: TextView
    private lateinit var user: User
    private lateinit var userId: TextView
    private lateinit var name: TextView
    private lateinit var email: TextView

    private lateinit var myRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        progressBar = findViewById(R.id.pbPost)

        user = intent.getSerializableExtra("user") as User

        userId = findViewById<TextView>(R.id.tvUser)
        name = findViewById<TextView>(R.id.tvNameU)
        email = findViewById<TextView>(R.id.tvEmailU)
        tvNoData = findViewById(R.id.tvNoData)

        postList = ArrayList()
        userId.text = user.id.toString()
        name.text = user.name
        email.text = user.email

        myRecyclerView = findViewById(R.id.myRecyclerViewP)

        loadPosts(user.id)
    }

    private fun loadPosts(idUSer: Int) {
        progressBar.visibility = View.VISIBLE

        launch {
            try {
                val apiResponse = ApiAdapter.apiClient.getListPosts(idUSer)

                if (apiResponse.isSuccessful && apiResponse.body() != null) {
                    val posts = apiResponse.body() as PostResponse

                    if(posts.data.isEmpty()) {
                        tvNoData.visibility = View.VISIBLE
                        myRecyclerView.visibility = View.GONE
                    } else {
                        tvNoData.visibility = View.GONE
                        myRecyclerView.visibility = View.VISIBLE
                        initRecycler(posts)
                        Log.v("APIDATA", "Data: $posts")
                    }
                    progressBar.visibility = View.GONE
                }
            } catch (e: Exception) {
                Log.v("APIDATA", "Exception: {${e.localizedMessage}")
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun initRecycler(posts: PostResponse) {
        postList = posts.data

        layoutManager = LinearLayoutManager(this)
        myRecyclerView.layoutManager = layoutManager

        postAdapter = PostAdapter(postList, this)
        myRecyclerView.adapter = postAdapter
    }
}