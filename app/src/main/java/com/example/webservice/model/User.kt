package com.example.webservice.model

import java.io.Serializable

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val gender: String,
    val status: String
) : Serializable
