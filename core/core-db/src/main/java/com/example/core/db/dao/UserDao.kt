package com.example.core.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.db.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: UserEntity): Unit

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getByUsername(username: String): UserEntity?

    @Query("UPDATE users SET active = :active WHERE username = :username")
    suspend fun setActive(username: String, active: Boolean)

    @Query("SELECT * FROM users WHERE active = 1 LIMIT 1")
    suspend fun getActiveUser(): UserEntity?
}