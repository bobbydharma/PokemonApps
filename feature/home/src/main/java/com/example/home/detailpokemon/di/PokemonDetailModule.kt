package com.example.home.detailpokemon.di

import com.example.core_network.PokeApiService
import com.example.home.detailpokemon.data.repository.PokemonDetailRepositoryImpl
import com.example.home.detailpokemon.domain.repository.PokemonDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonDetailModule {
    @Provides
    @Singleton
    fun providePokemonDetailRepository(api: PokeApiService): PokemonDetailRepository {
        return PokemonDetailRepositoryImpl(api)
    }
}