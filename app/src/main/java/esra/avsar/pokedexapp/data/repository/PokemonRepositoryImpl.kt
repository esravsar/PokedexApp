package esra.avsar.pokedexapp.data.repository

import esra.avsar.pokedexapp.domain.model.Pokemon
import esra.avsar.pokedexapp.domain.model.PokemonDetail
import esra.avsar.pokedexapp.domain.repository.PokemonRepository
import esra.avsar.pokedexapp.domain.usecase.getpokemondetail.GetPokemonDetailUseCase
import esra.avsar.pokedexapp.domain.usecase.getpokemons.GetPokemonsUseCase
import esra.avsar.pokedexapp.util.Resource
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase

) : PokemonRepository {
    override suspend fun getPokemons(): Resource<List<Pokemon?>> {
        return getPokemonsUseCase.executeGetPokemons()
    }

    override suspend fun getPokemonDetail(pokemonId: String): Resource<PokemonDetail?> {
        return getPokemonDetailUseCase.executeGetPokemonDetail(pokemonId)
    }
}