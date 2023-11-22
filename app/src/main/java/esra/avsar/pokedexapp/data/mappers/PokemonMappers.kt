package esra.avsar.pokedexapp.data.mappers

import esra.avsar.pokedexapp.data.remote.dto.PokemonResultDto
import esra.avsar.pokedexapp.domain.model.Pokemon

/**
 * Created by EsraAvsar on 22.11.2023.
 */
fun PokemonResultDto.toPokemonResult() = Pokemon(
    name = name,
    url = url
)