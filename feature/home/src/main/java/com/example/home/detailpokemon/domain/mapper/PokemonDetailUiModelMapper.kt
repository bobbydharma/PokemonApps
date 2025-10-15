package com.example.home.detailpokemon.domain.mapper

import com.example.home.detailpokemon.domain.model.PokemonDetailDomainModel
import com.example.home.detailpokemon.ui.model.PokemonDetailUiModel
import com.example.home.detailpokemon.ui.model.StatUiModel

fun PokemonDetailDomainModel.toUiModel(): PokemonDetailUiModel {
    return PokemonDetailUiModel(
        name = name,
        imageUrl = imageUrl,
        types = types,
        height = height,
        weight = weight,
        stats = stats.map {
            StatUiModel(
                it.name,
                it.baseStat
            )
        }
    )
}