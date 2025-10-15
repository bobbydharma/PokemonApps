package com.example.auth.login.domain.repository

import com.example.core.db.entity.UserEntity

interface LoginRepository {
    suspend fun login(user: UserEntity)
}