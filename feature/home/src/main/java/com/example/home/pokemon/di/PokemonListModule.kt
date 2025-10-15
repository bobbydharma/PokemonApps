package com.example.home.pokemon.di

import com.example.core_network.PokeApiService
import com.example.home.pokemon.data.repository.PokemonRepositoryImpl
import com.example.home.pokemon.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonListModule {
    @Provides
    @Singleton
    fun providePokemonRepository(api: PokeApiService): PokemonRepository {
        return PokemonRepositoryImpl(api)
    }
}