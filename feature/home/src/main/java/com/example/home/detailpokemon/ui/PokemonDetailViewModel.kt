package com.example.home.detailpokemon.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.db.uitls.DataState
import com.example.core.db.uitls.asMutableStateFlow
import com.example.home.detailpokemon.domain.mapper.toUiModel
import com.example.home.detailpokemon.domain.repository.PokemonDetailRepository
import com.example.home.detailpokemon.ui.model.PokemonDetailUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonDetailRepository
) : ViewModel() {
    private val _pokemonDetail = MutableStateFlow<DataState<PokemonDetailUiModel>>(DataState.Empty)
    val pokemonDetail = _pokemonDetail.asStateFlow()

    fun getDetailPokemon(name: String) = viewModelScope.launch(Dispatchers.IO) {
        _pokemonDetail.asMutableStateFlow { repository.getPokemonDetail(name).toUiModel() }
    }
}