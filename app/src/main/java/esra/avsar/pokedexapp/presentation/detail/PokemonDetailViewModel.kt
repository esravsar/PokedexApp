package esra.avsar.pokedexapp.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import esra.avsar.pokedexapp.domain.model.PokemonDetail
import esra.avsar.pokedexapp.domain.model.PokemonDetailAbout
import esra.avsar.pokedexapp.domain.repository.PokemonRepository
import esra.avsar.pokedexapp.util.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) :
    ViewModel() {
    val pokemonDetail = MutableLiveData<Resource<PokemonDetail?>>()
    val pokemonDetailAbout = MutableLiveData<Resource<PokemonDetailAbout?>>()
    val pokemonLoading = MutableLiveData<Resource<Boolean>>()
    val pokemonError = MutableLiveData<Resource<Boolean>>()

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        pokemonError.value = Resource.error(throwable.localizedMessage ?: "Error!", true)
    }

    fun getPokemonDetail(pokemonId: Int) {
        pokemonLoading.value = Resource.loading(true)

        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val detailResource = pokemonRepository.getPokemonDetail(pokemonId)
            val detailAboutResource = pokemonRepository.getPokemonDetailAbout(pokemonId)
            withContext(Dispatchers.Main) {
                pokemonDetail.value = detailResource
                pokemonDetailAbout.value = detailAboutResource
                pokemonLoading.value = Resource.loading(false)
                pokemonError.value = Resource.error("", false)
            }
        }
    }
}