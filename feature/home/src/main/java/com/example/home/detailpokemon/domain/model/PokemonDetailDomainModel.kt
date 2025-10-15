package com.example.home.detailpokemon.domain.model

data class PokemonDetailDomainModel(
    val name: String,
    val imageUrl: String,
    val types: List<String>,
    val height: Int,
    val weight: Int,
    val stats: List<StatDomainModel>
)

data class StatDomainModel(
    val name: String,
    val baseStat: Int
)