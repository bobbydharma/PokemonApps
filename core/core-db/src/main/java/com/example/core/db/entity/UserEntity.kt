package com.example.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val email: String,
    val password: String,
    val name: String,
    val gender: String,
    val address: String,
    val active: Boolean = false
)