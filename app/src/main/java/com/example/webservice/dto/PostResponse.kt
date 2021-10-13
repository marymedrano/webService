package com.example.webservice.dto

import com.example.webservice.model.Post
import com.google.gson.annotations.SerializedName

data class PostResponse(@SerializedName("data") var data: List<Post>)
