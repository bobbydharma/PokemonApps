package com.example.pokemon

import com.example.core.db.dao.UserDao
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dao: UserDao
): MainRepository {
    override suspend fun isLoggedIn(): Boolean {
        val activeUser = dao.getActiveUser()
        return activeUser != null
    }

}