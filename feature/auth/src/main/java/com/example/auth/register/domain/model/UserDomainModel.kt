package com.example.auth.register.domain.model

class UserDomainModel(
    val username: String,
    val passwordHash: String,
    val active: Boolean
)