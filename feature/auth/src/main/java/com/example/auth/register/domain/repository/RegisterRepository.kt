package com.example.auth.register.domain.repository

import com.example.core.db.entity.UserEntity

interface RegisterRepository {
    suspend fun register(user: UserEntity)
}