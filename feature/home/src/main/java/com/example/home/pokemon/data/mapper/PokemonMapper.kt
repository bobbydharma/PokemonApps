package com.example.home.pokemon.data.mapper

import com.example.core.db.entity.PokemonEntity
import com.example.core_network.model.PokemonResult
import com.example.home.pokemon.domain.model.PokemonDomainModel

fun PokemonResult.toDomain(): PokemonDomainModel {
    val id = url?.trimEnd('/')?.split("/")?.last()?.toInt() ?: 0
    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    return PokemonDomainModel(id, name.orEmpty(), imageUrl)
}

fun PokemonResult.toEntity(): PokemonEntity{
    val id = url?.trimEnd('/')?.split("/")?.last()?.toInt() ?: 0
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    return PokemonEntity(
        name = name.orEmpty(),
        imageUrl = imageUrl,
        id = id
    )
}

fun PokemonEntity.toDomain(): PokemonDomainModel {
    return PokemonDomainModel(
        id, name, imageUrl
    )
}