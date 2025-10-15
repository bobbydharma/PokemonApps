package com.example.pokemon

interface MainRepository {
    suspend fun isLoggedIn(): Boolean
}