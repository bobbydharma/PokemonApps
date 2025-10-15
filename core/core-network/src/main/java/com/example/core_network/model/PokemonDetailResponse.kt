package com.example.core_network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<PokemonTypeSlot>?,
    val height: Int?,
    val weight: Int? = 0,
    val stats: List<Stats>?
)

data class Stats(
    @SerializedName("base_stat")
    val baseStat: Int?,
    val stat: Stat?
)

data class Stat(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)

data class Sprites(
    val front_default: String?,
    val back_default: String?,
    val other: OtherSprites?
)

data class OtherSprites(
    val official_artwork: OfficialArtwork?
)

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String?
)

data class PokemonTypeSlot(
    val slot: Int?,
    val type: PokemonType?
)

data class PokemonType(
    val name: String?
)