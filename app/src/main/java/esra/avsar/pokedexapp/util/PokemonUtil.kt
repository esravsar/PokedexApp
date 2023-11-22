package esra.avsar.pokedexapp.util

import esra.avsar.pokedexapp.R

/**
 * Created by EsraAvsar on 22.11.2023.
 */
object PokemonUtil {
    fun getDetailColorByType(pokemonType: String?): Int = when (pokemonType) {
        "bug" -> R.color.pokemon_type_bug
        "dark" -> R.color.pokemon_type_dark
        "dragon" -> R.color.pokemon_type_dragon
        "electric" -> R.color.pokemon_type_electric
        "fairy" -> R.color.pokemon_type_fairy
        "fighting" -> R.color.pokemon_type_fighting
        "fire" -> R.color.pokemon_type_fire
        "flying" -> R.color.pokemon_type_flying
        "ghost" -> R.color.pokemon_type_ghost
        "grass" -> R.color.pokemon_type_grass
        "ground" -> R.color.pokemon_type_ground
        "ice" -> R.color.pokemon_type_ice
        "normal" -> R.color.pokemon_type_normal
        "poison" -> R.color.pokemon_type_poison
        "psychic" -> R.color.pokemon_type_psychic
        "rock" -> R.color.pokemon_type_rock
        "steel" -> R.color.pokemon_type_steel
        "water" -> R.color.pokemon_type_water
        else -> R.color.grayscale_medium
    }
}