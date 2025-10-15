package com.example.home.detailpokemon.data.mapper

import com.example.core_network.model.PokemonDetailResponse
import com.example.home.detailpokemon.domain.model.PokemonDetailDomainModel
import com.example.home.detailpokemon.domain.model.StatDomainModel

fun PokemonDetailResponse.toDomainModel(): PokemonDetailDomainModel {
    return PokemonDetailDomainModel(
        name = name,
        imageUrl = sprites.front_default.orEmpty(),
        types = types?.map { it.type?.name.orEmpty() } ?: emptyList() ,
        height = height ?: 0,
        weight = weight ?: 0,
        stats = stats?.map{
            StatDomainModel(
                name = it.stat?.name.orEmpty(),
                baseStat = it.baseStat ?: 0
            )
        } ?: emptyList()
    )
}