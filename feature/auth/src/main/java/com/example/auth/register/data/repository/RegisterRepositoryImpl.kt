package com.example.auth.register.data.repository

import com.example.auth.register.domain.repository.RegisterRepository
import com.example.core.db.dao.UserDao
import com.example.core.db.entity.UserEntity
import com.example.core.db.uitls.safeApiCall
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val dao: UserDao
): RegisterRepository {
    override suspend fun register(user: UserEntity) {
        return safeApiCall {
            val existingUser = dao.getByUsername(user.email)
            if (existingUser != null) {
                throw Exception("Email already registered")
            }
            dao.insert(user)
        }
    }
}