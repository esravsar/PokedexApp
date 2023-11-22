package esra.avsar.pokedexapp.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by EsraAvsar on 20.11.2023.
 */
data class TypesDto(
    @SerializedName("slot")
    val slot: Int?,
    @SerializedName("type")
    val type: TypeDto?
)