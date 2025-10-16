package com.example.auth.register.domain.model

data class UserRequest (
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val gender: String = "",
    val address: String = "",
    val active: Boolean = false
)