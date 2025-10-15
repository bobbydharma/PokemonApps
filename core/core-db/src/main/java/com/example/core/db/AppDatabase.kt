package com.example.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.db.dao.PokemonDao
import com.example.core.db.dao.UserDao
import com.example.core.db.entity.PokemonEntity
import com.example.core.db.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        PokemonEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun userDao(): UserDao
}