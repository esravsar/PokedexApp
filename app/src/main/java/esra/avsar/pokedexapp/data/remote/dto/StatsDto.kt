package esra.avsar.pokedexapp.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by EsraAvsar on 20.11.2023.
 */
data class StatsDto(
    @SerializedName("base_stat")
    val baseStat: Int?,
    @SerializedName("effort")
    val effort: Int?,
    @SerializedName("stat")
    val stat: StatDto?
)