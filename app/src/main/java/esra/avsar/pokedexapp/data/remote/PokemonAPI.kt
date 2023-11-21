package esra.avsar.pokedexapp.data.remote

import esra.avsar.pokedexapp.data.remote.dto.PokemonResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by EsraAvsar on 20.11.2023.
 */
interface PokemonAPI {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 150,
        @Query("offset") offset: Int = 0
    ): Response<PokemonResponseDto>

}