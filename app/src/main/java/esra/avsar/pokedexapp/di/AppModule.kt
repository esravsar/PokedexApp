package esra.avsar.pokedexapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import esra.avsar.pokedexapp.data.remote.PokemonAPI
import esra.avsar.pokedexapp.data.repository.PokemonRepositoryImpl
import esra.avsar.pokedexapp.domain.repository.PokemonRepository
import esra.avsar.pokedexapp.domain.usecase.getpokemons.GetPokemonsUseCase
import esra.avsar.pokedexapp.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by EsraAvsar on 20.11.2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): PokemonAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonAPI::class.java)
    }

    @Singleton
    @Provides
    fun providePokemonRepository(
        pokemonUseCase: GetPokemonsUseCase
    ) = PokemonRepositoryImpl(pokemonUseCase) as PokemonRepository
}