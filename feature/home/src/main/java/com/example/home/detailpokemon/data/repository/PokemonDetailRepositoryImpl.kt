package com.example.home.detailpokemon.data.repository

import com.example.core.db.uitls.safeApiCall
import com.example.core_network.PokeApiService
import com.example.home.detailpokemon.data.mapper.toDomainModel
import com.example.home.detailpokemon.domain.model.PokemonDetailDomainModel
import com.example.home.detailpokemon.domain.repository.PokemonDetailRepository
import javax.inject.Inject

class PokemonDetailRepositoryImpl @Inject constructor(
    private val api: PokeApiService
) : PokemonDetailRepository {
    override suspend fun getPokemonDetail(name: String): PokemonDetailDomainModel =
        safeApiCall {
            api.getPokemon(name).toDomainModel()
        }
}
