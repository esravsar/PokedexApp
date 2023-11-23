package esra.avsar.pokedexapp.domain.model

import java.io.Serializable

/**
 * Created by EsraAvsar on 21.11.2023.
 */
data class Pokemon(
    val name: String?,
    val formattedId: String?,
    val id: Int,
    val image: String
) : Serializable