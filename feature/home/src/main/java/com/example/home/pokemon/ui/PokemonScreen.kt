package com.example.home.pokemon.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.home.pokemon.domain.model.PokemonDomainModel

@Composable
fun PokemonScreen(
    viewModel: PokemonViewModel = hiltViewModel(),
    onClick: (String) -> Unit
) {
    val pokemonItems = viewModel.pokemonPagingFlow.collectAsLazyPagingItems()
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        // 🔍 Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                viewModel.onQueryChange(it)
            },
            label = { Text("Cari Pokémon") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true
        )

        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(pokemonItems.itemCount) { index ->
                    pokemonItems[index]?.let { pokemon ->
                        PokemonCard(pokemon, onClick = {
                            println("Bobby ===> ini ke klik")
                            onClick(pokemon.name)
                        })
                    }
                }

                pokemonItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item(span = { GridItemSpan(2) }) {
                                LoadingItem("Memuat Pokémon...")
                            }
                        }

                        loadState.append is LoadState.Loading -> {
                            item(span = { GridItemSpan(2) }) {
                                LoadingItem("Memuat lebih banyak...")
                            }
                        }

                        loadState.refresh is LoadState.Error -> {
                            val e = pokemonItems.loadState.refresh as LoadState.Error
                            item(span = { GridItemSpan(2) }) {
                                ErrorItem(
                                    e.error.message ?: "Terjadi kesalahan",
                                    pokemonItems::retry
                                )
                            }
                        }

                        loadState.append is LoadState.Error -> {
                            val e = pokemonItems.loadState.append as LoadState.Error
                            item(span = { GridItemSpan(2) }) {
                                ErrorItem(
                                    e.error.message ?: "Gagal memuat tambahan",
                                    pokemonItems::retry
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonCard(
    pokemon: PokemonDomainModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun LoadingItem(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Text(
                text = message,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun ErrorItem(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message, color = Color.Red)
        Button(onClick = onRetry, modifier = Modifier.padding(top = 8.dp)) {
            Text("Coba Lagi")
        }
    }
}