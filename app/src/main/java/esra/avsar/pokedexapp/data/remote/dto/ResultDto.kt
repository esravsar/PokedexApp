package esra.avsar.pokedexapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import esra.avsar.pokedexapp.domain.model.Pokemon

/**
 * Created by EsraAvsar on 20.11.2023.
 */
data class ResultDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
) {
    fun toPokemonResult() = Pokemon(
        name = name,
        url = url
    )
}