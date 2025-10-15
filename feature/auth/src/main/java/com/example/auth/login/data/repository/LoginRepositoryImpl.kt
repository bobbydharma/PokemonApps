package com.example.auth.login.data.repository

import com.example.auth.login.domain.repository.LoginRepository
import com.example.core.db.dao.UserDao
import com.example.core.db.entity.UserEntity
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dao: UserDao
) : LoginRepository {
    override suspend fun login(user: UserEntity) {
        val existingUser = dao.getByUsername(user.username)
        dao.setActive(user.username, true)
        if (existingUser != null) {
            if (existingUser.password != user.password){
                throw Exception("Email atau Password salah")
            }
        } else {
            throw Exception("Email belum terdaftar")
        }
    }
}