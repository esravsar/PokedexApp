package esra.avsar.pokedexapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import esra.avsar.pokedexapp.R
import esra.avsar.pokedexapp.databinding.FragmentPokemonDetailBinding
import esra.avsar.pokedexapp.extension.capitalizeFirstChar
import esra.avsar.pokedexapp.extension.formatPokemonId
import esra.avsar.pokedexapp.extension.formatPokemonStat

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {
    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PokemonDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonDetailViewModel::class.java)
        val bundle: PokemonDetailFragmentArgs by navArgs()
        val incomingPokemonId = bundle.id

        binding.ivDetailBackArrow.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_pokemonDetailFragment_to_pokemonListFragment)
        }

        val formattedPokemonId = incomingPokemonId.formatPokemonId()
        binding.tvDetailPokemonId.text = formattedPokemonId

        val imageUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${incomingPokemonId}.png"
        Glide.with(requireContext())
            .load(imageUrl)
            .into(binding.ivDetailPokemon)

        viewModel.getPokemonDetail(incomingPokemonId.toString())

        viewModel.getPokemonDetailAbout(incomingPokemonId.toString())

        viewModel.pokemonDetailAbout.observe(viewLifecycleOwner, Observer { pokemonDetailAbout ->
            binding.tvDetailAboutText.text =
                pokemonDetailAbout.data?.flavorTextEntries?.get(9)?.flavorText?.replace("\n", " ")
        })

        viewModel.pokemon.observe(viewLifecycleOwner, Observer { pokemonDetails ->
            with(binding) {
                tvDetailPokemonName.text = pokemonDetails.data?.name?.capitalizeFirstChar()

                pokemonDetails.data?.types?.forEach { pokemonType ->
                    val pokemonTypeSlot = pokemonType.slot

                    when (pokemonTypeSlot) {
                        1 -> {
                            tvDetailPokemonTypeOne.text =
                                pokemonType.type?.name?.capitalizeFirstChar()
                        }

                        2 -> {
                            tvDetailPokemonTypeTwo.text =
                                pokemonType.type?.name?.capitalizeFirstChar()
                        }
                    }
                }
                if (pokemonDetails.data?.types?.size == 1) {
                    tvDetailPokemonTypeTwo.visibility = View.GONE
                } else {
                    tvDetailPokemonTypeTwo.visibility = View.VISIBLE
                }

                val weight = pokemonDetails.data?.weight
                val formattedWeight = weight?.toDouble()?.div(10).toString().replace(".", ",")
                tvDetailPokemonWeight.text = "$formattedWeight kg"

                val height = pokemonDetails.data?.height
                val formattedHeight = height?.toDouble()?.div(10).toString().replace(".", ",")
                tvDetailPokemonHeight.text = "$formattedHeight m"

                pokemonDetails.data?.abilities?.forEach { pokemonAbility ->
                    val pokemonAbilitySlot = pokemonAbility.slot

                    when (pokemonAbilitySlot) {
                        1 -> {
                            tvDetailPokemonMovesOne.text =
                                pokemonAbility.ability?.name?.capitalizeFirstChar()

                        }

                        3 -> {
                            tvDetailPokemonMovesTwo.text =
                                pokemonAbility.ability?.name?.capitalizeFirstChar()
                        }
                    }
                }
                if (pokemonDetails.data?.abilities?.size == 1) {
                    tvDetailPokemonMovesTwo.visibility = View.GONE
                } else {
                    tvDetailPokemonMovesTwo.visibility = View.VISIBLE
                }

                pokemonDetails.data?.stats?.forEach { pokemonStats ->
                    val pokemonStatName = pokemonStats.stat?.name

                    when (pokemonStatName) {
                        "hp" -> {
                            if (pokemonStats.baseStat != null) {
                                pbHP.progress = pokemonStats.baseStat
                            }
                            tvDetailPokemonHP.text = pokemonStats.baseStat?.formatPokemonStat()
                        }

                        "attack" -> {
                            if (pokemonStats.baseStat != null) {
                                pbATK.progress = pokemonStats.baseStat
                            }
                            tvDetailPokemonATK.text = pokemonStats.baseStat?.formatPokemonStat()
                        }

                        "defense" -> {
                            if (pokemonStats.baseStat != null) {
                                pbDEF.progress = pokemonStats.baseStat
                            }
                            tvDetailPokemonDEF.text = pokemonStats.baseStat?.formatPokemonStat()
                        }

                        "special-attack" -> {
                            if (pokemonStats.baseStat != null) {
                                pbSATK.progress = pokemonStats.baseStat
                            }
                            tvDetailPokemonSATK.text = pokemonStats.baseStat?.formatPokemonStat()
                        }

                        "special-defense" -> {
                            if (pokemonStats.baseStat != null) {
                                pbSDEF.progress = pokemonStats.baseStat
                            }
                            tvDetailPokemonSDEF.text = pokemonStats.baseStat?.formatPokemonStat()
                        }

                        "speed" -> {
                            if (pokemonStats.baseStat != null) {
                                pbSPD.progress = pokemonStats.baseStat
                            }
                            tvDetailPokemonSPD.text = pokemonStats.baseStat?.formatPokemonStat()
                        }
                    }
                }
            }
        })
    }
}