package esra.avsar.pokedexapp.domain.usecase.getpokemondetail

import esra.avsar.pokedexapp.data.mappers.toPokemonDetail
import esra.avsar.pokedexapp.data.mappers.toPokemonDetailAbout
import esra.avsar.pokedexapp.data.remote.PokemonAPI
import esra.avsar.pokedexapp.domain.model.PokemonDetail
import esra.avsar.pokedexapp.domain.model.PokemonDetailAbout
import esra.avsar.pokedexapp.util.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

/**
 * Created by EsraAvsar on 20.11.2023.
 */
class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonAPI: PokemonAPI
) {
    private suspend fun <T, R> executePokemonDetail(
        call: suspend () -> Response<T>,
        mapper: (T) -> R
    ): Resource<R?> = try {
        val response = call.invoke()

        if (response?.isSuccessful == true) {
            response.body()?.let { data ->
                Resource.success(mapper.invoke(data))
            } ?: Resource.error("No pokemon found", null)
        } else {
            Resource.error("No pokemon found", null)
        }
    } catch (e: HttpException) {
        Resource.error("Error!", null)
    } catch (e: IOException) {
        Resource.error("Could not reach internet", null)
    }

    suspend fun executeGetPokemonDetail(pokemonId: Int): Resource<PokemonDetail?> =
        executePokemonDetail(
            { pokemonAPI.getPokemonDetail(pokemonId) },
            { it.toPokemonDetail() }
        )

    suspend fun executeGetPokemonDetailAbout(pokemonId: Int): Resource<PokemonDetailAbout?> =
        executePokemonDetail(
            { pokemonAPI.getPokemonDetailAbout(pokemonId) },
            { it.toPokemonDetailAbout() }
        )
}