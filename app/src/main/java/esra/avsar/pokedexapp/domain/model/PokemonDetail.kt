package esra.avsar.pokedexapp.domain.model

import java.io.Serializable

/**
 * Created by EsraAvsar on 20.11.2023.
 */
data class PokemonDetail(
    val height: Int?,
    val abilities: List<Abilities>?,
    val id: Int?,
    val name: String?,
    val stats: List<Stats>?,
    val types: List<Types>?,
    val weight: Int?
) : Serializable