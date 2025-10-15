package com.example.home.detailpokemon.ui.model

data class PokemonDetailUiModel(
    val name: String,
    val imageUrl: String,
    val types: List<String>,
    val height: Int,
    val weight: Int,
    val stats: List<StatUiModel>
)

data class StatUiModel(
    val name: String,
    val baseStat: Int
)