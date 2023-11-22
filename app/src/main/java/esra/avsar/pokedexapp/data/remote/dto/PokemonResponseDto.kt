package esra.avsar.pokedexapp.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by EsraAvsar on 20.11.2023.
 */
data class PokemonResponseDto(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("results")
    val results: List<PokemonResultDto?>?
)