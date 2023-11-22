package esra.avsar.pokedexapp.extension

/**
 * Created by EsraAvsar on 21.11.2023.
 */
fun Int.formatPokemonId(): String {
    return when {
        this < 10 -> "#00$this"
        this < 100 -> "#0$this"
        else -> "#$this"
    }
}

fun String.capitalizeFirstChar() = replaceFirstChar(Char::titlecase)

fun Int.formatPokemonStat(): String {
    return when {
        this < 10 -> "00$this"
        this < 100 -> "0$this"
        else -> "$this"
    }
}