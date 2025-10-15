package com.example.home.pokemon.domain.repository

import androidx.paging.PagingData
import com.example.home.pokemon.domain.model.PokemonDomainModel
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonPagingData(query: String?): Flow<PagingData<PokemonDomainModel>>
}