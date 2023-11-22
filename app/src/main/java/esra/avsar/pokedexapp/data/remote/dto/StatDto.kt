package esra.avsar.pokedexapp.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by EsraAvsar on 20.11.2023.
 */
data class StatDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)