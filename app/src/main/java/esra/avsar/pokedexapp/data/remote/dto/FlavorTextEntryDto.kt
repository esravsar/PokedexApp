package esra.avsar.pokedexapp.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by EsraAvsar on 22.11.2023.
 */
data class FlavorTextEntryDto(
    @SerializedName("flavor_text")
    val flavorText: String?
)