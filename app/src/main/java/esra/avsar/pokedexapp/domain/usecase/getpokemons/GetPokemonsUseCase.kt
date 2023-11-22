package esra.avsar.pokedexapp.domain.usecase.getpokemons

import esra.avsar.pokedexapp.data.mappers.toPokemonResult
import esra.avsar.pokedexapp.data.remote.PokemonAPI
import esra.avsar.pokedexapp.domain.model.Pokemon
import esra.avsar.pokedexapp.util.Resource
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

/**
 * Created by EsraAvsar on 20.11.2023.
 */
class GetPokemonsUseCase @Inject constructor(
    private val pokemonAPI: PokemonAPI
) {
    suspend fun executeGetPokemons(): Resource<List<Pokemon?>> {
        return try {
            val response = pokemonAPI.getPokemonList()
            if (response?.isSuccessful == true) {
                response.body()?.let { pokemonListDto ->
                    return@let Resource.success(pokemonListDto.results?.map { it?.toPokemonResult() })
                } ?: Resource.error("No pokemon found", null)
            } else {
                Resource.error("No pokemon found", null)
            }
        } catch (e: HttpException) {
            Resource.error("Error!", null)
        } catch (e: IOError) {
            Resource.error("Could not reach internet", null)
        }
    }
}