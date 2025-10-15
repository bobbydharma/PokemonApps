package com.example.auth.register.domain.model

data class UserRequest (
    val username: String,
    val password: String,
    val active: Boolean
)