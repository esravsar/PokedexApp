package esra.avsar.pokedexapp.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import esra.avsar.pokedexapp.domain.model.Pokemon
import esra.avsar.pokedexapp.domain.repository.PokemonRepository
import esra.avsar.pokedexapp.util.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    val pokemonList = MutableLiveData<Resource<List<Pokemon?>>>()
    val pokemonLoading = MutableLiveData<Resource<Boolean>>()
    val pokemonError = MutableLiveData<Resource<Boolean>>()
    val searchPokemonList: MutableList<Pokemon?> = mutableListOf()

    fun setList(list: List<Pokemon?>) {
        searchPokemonList.clear()
        searchPokemonList.addAll(list)
    }

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        pokemonError.value = Resource.error(throwable.localizedMessage ?: "Error!", true)
    }

    fun loadPokemons() {
        pokemonLoading.value = Resource.loading(true)

        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val resource = pokemonRepository.getPokemons()
            withContext(Dispatchers.Main) {
                if (resource.data.isNullOrEmpty()) {
                    pokemonError.value = Resource.error("", true)
                    pokemonLoading.value = Resource.loading(false)
                }
                resource.data?.let {
                    pokemonLoading.value = Resource.loading(false)
                    pokemonError.value = Resource.error("", false)
                    pokemonList.value = resource
                }
            }
        }
    }
}