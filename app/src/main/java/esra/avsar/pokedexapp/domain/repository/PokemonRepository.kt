package esra.avsar.pokedexapp.domain.repository

import esra.avsar.pokedexapp.domain.model.Pokemon
import esra.avsar.pokedexapp.domain.model.PokemonDetail
import esra.avsar.pokedexapp.util.Resource

/**
 * Created by EsraAvsar on 20.11.2023.
 */
interface PokemonRepository {
    suspend fun getPokemons(): Resource<List<Pokemon?>>

    suspend fun getPokemonDetail(pokemonId: String): Resource<PokemonDetail?>
}