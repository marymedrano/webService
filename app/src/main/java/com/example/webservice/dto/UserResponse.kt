package com.example.webservice.dto

import com.example.webservice.model.Meta
import com.example.webservice.model.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("meta") var meta: Meta,
    @SerializedName("data") var data: List<User>
)
