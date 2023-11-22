package esra.avsar.pokedexapp.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by EsraAvsar on 22.11.2023.
 */
data class PokemonDetailAboutDto(
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntryDto>?,
    @SerializedName("id")
    val id: Int?
)