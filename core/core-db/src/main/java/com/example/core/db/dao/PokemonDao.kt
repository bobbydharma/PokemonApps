package com.example.core.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.db.entity.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemons ORDER BY id ASC")
    fun getPokemons(): PagingSource<Int, PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons WHERE name = :name LIMIT 1")
    suspend fun getByName(name: String): PokemonEntity?

    @Query("SELECT COUNT(*) FROM pokemons")
    suspend fun countPokemons(): Int

    @Query("DELETE FROM pokemons")
    suspend fun clearAll()
}