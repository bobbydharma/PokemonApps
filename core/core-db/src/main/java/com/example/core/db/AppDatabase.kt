package com.example.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.db.dao.PokemonDao
import com.example.core.db.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}