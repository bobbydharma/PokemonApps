package com.example.core.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.db.entity.PokemonEntity

interface PokemonDao {
    @Query("SELECT * FROM pokemons ORDER BY id LIMIT :limit OFFSET :offset")
    suspend fun getPokemons(offset: Int, limit: Int): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons WHERE name = :name LIMIT 1")
    suspend fun getByName(name: String): PokemonEntity?
}