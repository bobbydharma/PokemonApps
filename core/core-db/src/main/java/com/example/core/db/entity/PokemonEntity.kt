package com.example.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class PokemonEntity(
    @PrimaryKey val name: String,
    val id: Int,
    val abilitiesJson: String
)