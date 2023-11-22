package esra.avsar.pokedexapp.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by EsraAvsar on 20.11.2023.
 */
data class AbilitiesDto(
    @SerializedName("ability")
    val ability: AbilityDto?,
    @SerializedName("is_hidden")
    val isHidden: Boolean?,
    @SerializedName("slot")
    val slot: Int?
)