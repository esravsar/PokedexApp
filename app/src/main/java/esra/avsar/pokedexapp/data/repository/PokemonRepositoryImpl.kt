package esra.avsar.pokedexapp.data.repository

import esra.avsar.pokedexapp.domain.model.Pokemon
import esra.avsar.pokedexapp.domain.repository.PokemonRepository
import esra.avsar.pokedexapp.domain.usecase.getpokemons.GetPokemonsUseCase
import esra.avsar.pokedexapp.util.Resource
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : PokemonRepository {
    override suspend fun getPokemons(): Resource<List<Pokemon?>> {
        return getPokemonsUseCase.executeGetPokemons()
    }
}