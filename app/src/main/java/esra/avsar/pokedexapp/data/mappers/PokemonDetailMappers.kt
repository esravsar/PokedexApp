package esra.avsar.pokedexapp.data.mappers

import esra.avsar.pokedexapp.data.remote.dto.AbilitiesDto
import esra.avsar.pokedexapp.data.remote.dto.AbilityDto
import esra.avsar.pokedexapp.data.remote.dto.PokemonDetailResponseDto
import esra.avsar.pokedexapp.data.remote.dto.StatDto
import esra.avsar.pokedexapp.data.remote.dto.StatsDto
import esra.avsar.pokedexapp.data.remote.dto.TypeDto
import esra.avsar.pokedexapp.data.remote.dto.TypesDto
import esra.avsar.pokedexapp.domain.model.Abilities
import esra.avsar.pokedexapp.domain.model.Ability
import esra.avsar.pokedexapp.domain.model.PokemonDetail
import esra.avsar.pokedexapp.domain.model.Stat
import esra.avsar.pokedexapp.domain.model.Stats
import esra.avsar.pokedexapp.domain.model.Type
import esra.avsar.pokedexapp.domain.model.Types

/**
 * Created by EsraAvsar on 22.11.2023.
 */
fun PokemonDetailResponseDto.toPokemonDetail() = PokemonDetail(
    abilities = abilities?.map { it.toAbilities() },
    height = height,
    id = id,
    name = name,
    weight = weight,
    stats = stats?.map { it.toStats() },
    types = types?.map { it.toTypes() }
)

fun AbilityDto.toAbility() = Ability(
    name = name,
    url = url
)

fun AbilitiesDto.toAbilities() = Abilities(
    ability = ability?.toAbility(),
    isHidden = isHidden,
    slot = slot
)

fun StatDto.toStat() = Stat(
    name = name,
    url = url
)

fun StatsDto.toStats() = Stats(
    baseStat = baseStat,
    effort = effort,
    stat = stat?.toStat()
)

fun TypeDto.toType() = Type(
    name = name
)

fun TypesDto.toTypes() = Types(
    slot = slot,
    type = type?.toType()
)