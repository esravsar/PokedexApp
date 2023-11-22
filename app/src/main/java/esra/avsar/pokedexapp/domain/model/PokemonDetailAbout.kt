package esra.avsar.pokedexapp.domain.model

import java.io.Serializable

/**
 * Created by EsraAvsar on 22.11.2023.
 */
data class PokemonDetailAbout(
    val flavorTextEntries: List<FlavorTextEntry>?,
    val id: Int?
) : Serializable