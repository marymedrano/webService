package com.example.webservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webservice.data.UsersAdapter
import com.example.webservice.dto.UserResponse
import com.example.webservice.model.User
import com.example.webservice.network.ApiAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope(), UsersAdapter.listenerUser{

    private lateinit var usersList : List<User>
    private var layoutManager: RecyclerView.LayoutManager?= null
    private var userAdapter: UsersAdapter? = null
    private lateinit var progressBar: ProgressBar

    private lateinit var myRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersList = ArrayList()

        myRecyclerView = findViewById(R.id.myRecyclerViewP)
        progressBar = findViewById(R.id.progressBar)

        loadUsers()
    }

    private fun loadUsers() {
        progressBar.visibility = View.VISIBLE

        launch {
            try {
                val apiResponse = ApiAdapter.apiClient.getListOfUsers()

                if (apiResponse.isSuccessful && apiResponse.body() != null) {
                    val users = apiResponse.body() as UserResponse

                    initRecycler(users)
                    Log.v("APIDATA", "Data: $users")
                    progressBar.visibility = View.GONE
                }
            } catch (e: Exception) {
                Log.v("APIDATA", "Exception: {${e.localizedMessage}")
                progressBar.visibility = View.GONE
            }

        }
    }

    private fun initRecycler(users: UserResponse) {
        usersList = users.data

        layoutManager = LinearLayoutManager(this)
        myRecyclerView.layoutManager = layoutManager

        userAdapter = UsersAdapter(usersList, this)
        myRecyclerView.adapter = userAdapter
        userAdapter?.setListenerUser(this)
    }

    override fun onClickListener(user: User) {
        //Toast.makeText(this, "User ID: " + user.id, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, PostActivity::class.java)
        intent.putExtra("user",user)
        startActivity(intent)
    }
}