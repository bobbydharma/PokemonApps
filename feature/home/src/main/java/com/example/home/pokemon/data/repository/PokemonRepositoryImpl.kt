package com.example.home.pokemon.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.core.db.AppDatabase
import com.example.core_network.PokeApiService
import com.example.home.pokemon.data.mapper.toDomain
import com.example.home.pokemon.domain.model.PokemonDomainModel
import com.example.home.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApiService,
    private val db: AppDatabase
) : PokemonRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPokemonPagingData(query: String?): Flow<PagingData<PokemonDomainModel>> {
        val pagingSourceFactory = { db.pokemonDao().getPokemons() }
        return if (!query.isNullOrBlank()){
            Pager(
                config = PagingConfig(pageSize = 1),
                pagingSourceFactory = { SearchPokemonPagingSource(api, query) }
            ).flow
        } else {
            Pager(
                config = PagingConfig(pageSize = 10, initialLoadSize = 10),
                remoteMediator = PokemonRemoteMediator(api, db),
                pagingSourceFactory = pagingSourceFactory
            ).flow.map { pagingData ->
                pagingData.map { it.toDomain() }
            }
        }

    }
}