package com.example.home.pokemon.data.repository

import android.graphics.pdf.LoadParams
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core_network.PokeApiService
import com.example.home.pokemon.data.mapper.toDomain
import com.example.home.pokemon.domain.model.PokemonDomainModel
import kotlinx.coroutines.delay
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val api: PokeApiService
) : PagingSource<Int, PokemonDomainModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonDomainModel> {
        return try {
            val offset = params.key ?: 0
            val response = api.getListPokemon(limit = params.loadSize, offset = offset)
            val pokemons = response.results?.map { it.toDomain() } ?: emptyList()

            delay(2000)

            LoadResult.Page(
                data = pokemons,
                prevKey = if (offset == 0) null else offset - params.loadSize,
                nextKey = if (response.next == null) null else offset + params.loadSize
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonDomainModel>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(state.config.pageSize)
                ?: page?.nextKey?.minus(state.config.pageSize)
        }
    }
}