package com.example.home.pokemon.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core_network.PokeApiService
import com.example.home.pokemon.domain.model.PokemonDomainModel
import kotlinx.coroutines.delay
import javax.inject.Inject

class SearchPokemonPagingSource @Inject constructor(
    private val api: PokeApiService,
    private val query: String
) : PagingSource<Int, PokemonDomainModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonDomainModel> {
        return try {
            delay(800) // opsional: simulasi loading
            val response = api.getPokemon(query.lowercase())

            val pokemon = PokemonDomainModel(
                id = response.id,
                name = response.name.orEmpty(),
                imageUrl = response.sprites.front_default.orEmpty()
            )

            LoadResult.Page(
                data = listOf(pokemon),
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonDomainModel>): Int? = null
}
