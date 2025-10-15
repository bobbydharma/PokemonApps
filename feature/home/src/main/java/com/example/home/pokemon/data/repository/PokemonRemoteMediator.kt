package com.example.home.pokemon.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.core.db.AppDatabase
import com.example.core.db.entity.PokemonEntity
import com.example.core_network.PokeApiService
import com.example.home.pokemon.data.mapper.toEntity
import kotlinx.coroutines.delay
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val api: PokeApiService,
    private val db: AppDatabase
) : RemoteMediator<Int, PokemonEntity>() {

    private val pokemonDao = db.pokemonDao()
    private var currentOffset = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        try {
            val offset = when (loadType) {
                LoadType.REFRESH -> {
                    currentOffset = 0
                    0
                }

                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> currentOffset
            }

            val limit = state.config.pageSize
            delay(3000)
            val response = api.getListPokemon(limit = limit, offset = offset)
            val results = response.results ?: emptyList()

            val entities = results.map { it.toEntity() }

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pokemonDao.clearAll()
                }
                pokemonDao.insertAll(entities)
            }

            val endOfPaginationReached = results.isEmpty()
            if (!endOfPaginationReached) {
                currentOffset += limit
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: retrofit2.HttpException) {
            return MediatorResult.Error(e)
        }
    }
}