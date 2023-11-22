package esra.avsar.pokedexapp.presentation.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import esra.avsar.pokedexapp.R
import esra.avsar.pokedexapp.databinding.FragmentPokemonListBinding

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PokemonListViewModel
    private lateinit var pokemonAdapter: PokemonListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonListViewModel::class.java)
        binding.rvPokemon.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        observeLiveData()

        if (Build.VERSION.SDK_INT >= 21) {
            val window: Window = requireActivity().window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = resources.getColor(R.color.red_bg)
        }

        viewModel.loadPokemons()
    }

    private fun observeLiveData() {
        viewModel.pokemonList.observe(viewLifecycleOwner, Observer { pokemons ->
            binding.rvPokemon.visibility = View.VISIBLE
            pokemonAdapter = PokemonListAdapter(ArrayList(pokemons.data))
            binding.rvPokemon.adapter = pokemonAdapter
        })

        viewModel.pokemonError.observe(viewLifecycleOwner, Observer { error ->
            error.data?.let {
                if (it) {
                    binding.tvErrorText.visibility = View.VISIBLE
                    binding.rvPokemon.visibility = View.GONE
                } else {
                    binding.tvErrorText.visibility = View.GONE
                }
            }
        })

        viewModel.pokemonLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading.data?.let {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvPokemon.visibility = View.GONE
                    binding.tvErrorText.visibility = View.GONE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }
}