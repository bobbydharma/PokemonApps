package com.example.auth.register.domain.model

class UserDomainModel(
    val email: String,
    val passwordHash: String,
    val name: String,
    val address: String,
    val gender: String,
    val active: Boolean
)