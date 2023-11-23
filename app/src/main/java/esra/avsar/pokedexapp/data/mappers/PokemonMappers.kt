package esra.avsar.pokedexapp.data.mappers

import esra.avsar.pokedexapp.data.remote.dto.PokemonResultDto
import esra.avsar.pokedexapp.domain.model.Pokemon
import esra.avsar.pokedexapp.extension.formatPokemonId

/**
 * Created by EsraAvsar on 22.11.2023.
 */
fun PokemonResultDto.toPokemonResult() = Pokemon(
    name = name,
    formattedId = url?.split("/").let { it?.get(it.size - 2)?.toIntOrNull() ?: 1 }
        .formatPokemonId(),
    id = url?.split("/").let { it?.get(it.size - 2)?.toIntOrNull() ?: 1 },
    image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${
        url?.split(
            "/"
        ).let { it?.get(it.size - 2)?.toIntOrNull() ?: 1 }
    }.png"
)