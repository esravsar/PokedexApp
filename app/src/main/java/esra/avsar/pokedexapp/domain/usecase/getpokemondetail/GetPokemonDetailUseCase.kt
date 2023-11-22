package esra.avsar.pokedexapp.domain.usecase.getpokemondetail

import esra.avsar.pokedexapp.data.mappers.toPokemonDetail
import esra.avsar.pokedexapp.data.mappers.toPokemonDetailAbout
import esra.avsar.pokedexapp.data.remote.PokemonAPI
import esra.avsar.pokedexapp.domain.model.PokemonDetail
import esra.avsar.pokedexapp.domain.model.PokemonDetailAbout
import esra.avsar.pokedexapp.util.Resource
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

/**
 * Created by EsraAvsar on 20.11.2023.
 */
class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonAPI: PokemonAPI
) {
    suspend fun executeGetPokemonDetail(pokemonId: String): Resource<PokemonDetail?> {
        return try {
            val response = pokemonAPI.getPokemonDetail(pokemonId)
            if (response?.isSuccessful == true) {
                response.body()?.let { pokemonDetailDto ->
                    return@let Resource.success(pokemonDetailDto.toPokemonDetail())
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

    suspend fun executeGetPokemonDetailAbout(pokemonId: String): Resource<PokemonDetailAbout?> {
        return try {
            val response = pokemonAPI.getPokemonDetailAbout(pokemonId)
            if (response?.isSuccessful == true) {
                response.body()?.let { pokemonDetailAboutDto ->
                    return@let Resource.success(pokemonDetailAboutDto.toPokemonDetailAbout())
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