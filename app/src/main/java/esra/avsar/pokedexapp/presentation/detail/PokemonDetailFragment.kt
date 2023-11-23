package esra.avsar.pokedexapp.presentation.detail

import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
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
import esra.avsar.pokedexapp.util.PokemonUtil

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

        binding.btnDetailLeftChevron.setOnClickListener {
            viewModel.loadPreviousPokemon()
        }

        binding.btnDetailRightChevron.setOnClickListener {
            viewModel.loadNextPokemon(150)
        }

        viewModel.currentPokemonId.observe(viewLifecycleOwner) { pokemonId ->
            binding.tvDetailPokemonId.text = pokemonId.formatPokemonId()
        }

        viewModel.currentImageUrl.observe(viewLifecycleOwner) { imageUrl ->
            loadImage(imageUrl)
        }

        val formattedPokemonId = incomingPokemonId.formatPokemonId()
        binding.tvDetailPokemonId.text = formattedPokemonId

        viewModel.updateInitialPokemonId(bundle.id)

        loadDetail()
    }

    private fun loadDetail() {
        viewModel.pokemonDetailAbout.observe(viewLifecycleOwner) { pokemonDetailAbout ->
            binding.tvDetailAboutText.text =
                pokemonDetailAbout.data?.flavorTextEntries?.get(9)?.flavorText?.replace("\n", " ")
        }

        viewModel.pokemonDetail.observe(viewLifecycleOwner) { pokemonDetails ->
            with(binding) {
                tvDetailPokemonName.text = pokemonDetails.data?.name?.capitalizeFirstChar()

                val currentPokemonId = pokemonDetails.data?.id ?: 1
                binding.btnDetailLeftChevron.visibility =
                    if (currentPokemonId == 1) View.INVISIBLE else View.VISIBLE
                binding.btnDetailRightChevron.visibility =
                    if (currentPokemonId == 150) View.INVISIBLE else View.VISIBLE

                pokemonDetails.data?.types?.forEach { pokemonType ->
                    val pokemonTypeSlot = pokemonType.slot
                    val pokemonColor =
                        PokemonUtil.getDetailColorByType(pokemonType.type?.name)

                    when (pokemonTypeSlot) {
                        1 -> {
                            tvDetailPokemonTypeOne.text =
                                pokemonType.type?.name?.capitalizeFirstChar()
                            setPokemonDetailColor(pokemonColor)

                            tvDetailPokemonTypeOne.background.setColorFilter(
                                setPokemonColor(
                                    pokemonColor
                                ), PorterDuff.Mode.SRC_IN
                            )
                        }

                        2 -> {
                            tvDetailPokemonTypeTwo.text =
                                pokemonType.type?.name?.capitalizeFirstChar()

                            tvDetailPokemonTypeTwo.background.setColorFilter(
                                setPokemonColor(
                                    pokemonColor
                                ), PorterDuff.Mode.SRC_IN
                            )
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
        }
    }

    private fun loadImage(imageUrl: String) {
        Glide.with(requireContext())
            .load(imageUrl)
            .into(binding.ivDetailPokemon)
    }

    fun setPokemonColor(pokemonColor: Int): Int {
        return ContextCompat.getColor(requireContext(), pokemonColor)
    }

    private fun setPokemonDetailColor(pokemonColor: Int) {
        with(binding) {
            clDetailPokemon.setBackgroundColor(setPokemonColor(pokemonColor))
            nstdDetailPokemon.setBackgroundColor(setPokemonColor(pokemonColor))

            if (Build.VERSION.SDK_INT >= 21) {
                val window: Window = requireActivity().window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = resources.getColor(pokemonColor)
            }

            tvDetailAbout.setTextColor(setPokemonColor(pokemonColor))

            tvDetailBaseStats.setTextColor(setPokemonColor(pokemonColor))

            tvDetailPokemonHPText.setTextColor(setPokemonColor(pokemonColor))
            tvDetailPokemonATKText.setTextColor(setPokemonColor(pokemonColor))
            tvDetailPokemonDEFText.setTextColor(setPokemonColor(pokemonColor))
            tvDetailPokemonSATKText.setTextColor(setPokemonColor(pokemonColor))
            tvDetailPokemonSDEFText.setTextColor(setPokemonColor(pokemonColor))
            tvDetailPokemonSPDText.setTextColor(setPokemonColor(pokemonColor))

            pbHP.progressTintList = requireContext().getColorStateList(pokemonColor)
            pbATK.progressTintList = requireContext().getColorStateList(pokemonColor)
            pbDEF.progressTintList = requireContext().getColorStateList(pokemonColor)
            pbSATK.progressTintList = requireContext().getColorStateList(pokemonColor)
            pbSDEF.progressTintList = requireContext().getColorStateList(pokemonColor)
            pbSPD.progressTintList = requireContext().getColorStateList(pokemonColor)

            pbHP.progressBackgroundTintList = requireContext().getColorStateList(pokemonColor)
            pbATK.progressBackgroundTintList = requireContext().getColorStateList(pokemonColor)
            pbDEF.progressBackgroundTintList = requireContext().getColorStateList(pokemonColor)
            pbSATK.progressBackgroundTintList = requireContext().getColorStateList(pokemonColor)
            pbSDEF.progressBackgroundTintList = requireContext().getColorStateList(pokemonColor)
            pbSPD.progressBackgroundTintList = requireContext().getColorStateList(pokemonColor)
        }
    }
}