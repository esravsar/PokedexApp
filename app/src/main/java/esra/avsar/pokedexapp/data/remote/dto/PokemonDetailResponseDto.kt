package esra.avsar.pokedexapp.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by EsraAvsar on 20.11.2023.
 */
data class PokemonDetailResponseDto(
    @SerializedName("height")
    val height: Int?,
    @SerializedName("abilities")
    val abilities: List<AbilitiesDto>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("stats")
    val stats: List<StatsDto>?,
    @SerializedName("types")
    val types: List<TypesDto>?,
    @SerializedName("weight")
    val weight: Int?
)