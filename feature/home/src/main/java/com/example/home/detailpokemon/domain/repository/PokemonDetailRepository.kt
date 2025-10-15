package com.example.home.detailpokemon.domain.repository

import com.example.home.detailpokemon.domain.model.PokemonDetailDomainModel

interface PokemonDetailRepository {
    suspend fun getPokemonDetail(name: String): PokemonDetailDomainModel
}