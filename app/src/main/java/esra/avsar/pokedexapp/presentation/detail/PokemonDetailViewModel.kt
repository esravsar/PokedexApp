package esra.avsar.pokedexapp.presentation.detail

import androidx.lifecycle.LiveData
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
import java.lang.Math.max
import java.lang.Math.min
import javax.inject.Inject

@HiltViewModel

class PokemonDetailViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) :
    ViewModel() {
    val pokemonDetail = MutableLiveData<Resource<PokemonDetail?>>()
    val pokemonDetailAbout = MutableLiveData<Resource<PokemonDetailAbout?>>()
    val pokemonLoading = MutableLiveData<Resource<Boolean>>()
    val pokemonError = MutableLiveData<Resource<Boolean>>()

    private val _currentPokemonId = MutableLiveData<Int>()
    val currentPokemonId: LiveData<Int>
        get() = _currentPokemonId

    private val _currentImageUrl = MutableLiveData<String>()
    val currentImageUrl: LiveData<String>
        get() = _currentImageUrl

    init {
        _currentPokemonId.value = 1
        updateImageUrl()
    }

    fun updateInitialPokemonId(pokemonId: Int) {
        _currentPokemonId.value = pokemonId
        getPokemonDetail(pokemonId)
        updateImageUrl()
    }

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

    fun loadPreviousPokemon() {
        _currentPokemonId.value = calculatePreviousPokemonId(_currentPokemonId.value ?: 1)
        getPokemonDetail(_currentPokemonId.value ?: 1)
        updateImageUrl()
    }

    fun loadNextPokemon(totalPokemonCount: Int) {
        _currentPokemonId.value =
            calculateNextPokemonId(_currentPokemonId.value ?: 1, totalPokemonCount)
        getPokemonDetail(_currentPokemonId.value ?: 1)
        updateImageUrl()
    }

    private fun updateImageUrl() {
        _currentImageUrl.value =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${_currentPokemonId.value}.png"
    }

    private fun calculatePreviousPokemonId(currentPokemonId: Int): Int {
        return max(currentPokemonId - 1, 1)
    }

    private fun calculateNextPokemonId(currentPokemonId: Int, totalPokemonCount: Int): Int {
        return min(currentPokemonId + 1, totalPokemonCount)
    }
}