package com.example.home.pokemon.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.core.db.AppDatabase
import com.example.core.db.entity.PokemonEntity
import com.example.core.db.entity.RemoteKeysEntity
import com.example.core_network.PokeApiService
import com.example.home.pokemon.data.mapper.toEntity

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val api: PokeApiService,
    private val db: AppDatabase
) : RemoteMediator<Int, PokemonEntity>() {

    private val pokemonDao = db.pokemonDao()
    private val remoteKeysDao = db.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    remoteKey?.nextKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            val limit = state.config.pageSize
            val offset = page * limit

            val response = api.getListPokemon(limit = limit, offset = offset)
            val results = response.results ?: emptyList()
            val endOfPaginationReached = results.isEmpty()

            val entities = results.map {
                it.toEntity()
            }

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeysDao.clearRemoteKeys()
                    pokemonDao.clearAll()
                }

                val keys = entities.map { pokemon ->
                    RemoteKeysEntity(
                        pokemonId = pokemon.id,
                        prevKey = if (page == 0) null else page - 1,
                        nextKey = if (endOfPaginationReached) null else page + 1
                    )
                }

                remoteKeysDao.insertAll(keys)
                pokemonDao.insertAll(entities)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PokemonEntity>
    ): RemoteKeysEntity? {
        val lastPage = state.pages.lastOrNull { it.data.isNotEmpty() } ?: return null
        val lastPokemon = lastPage.data.lastOrNull() ?: return null
        return db.remoteKeysDao().getRemoteKey(lastPokemon.id)
    }

}
