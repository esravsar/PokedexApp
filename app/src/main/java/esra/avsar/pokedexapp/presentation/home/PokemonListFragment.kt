package esra.avsar.pokedexapp.presentation.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import esra.avsar.pokedexapp.R
import esra.avsar.pokedexapp.databinding.FragmentPokemonListBinding

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PokemonListViewModel
    private val pokemonAdapter: PokemonListAdapter by lazy { PokemonListAdapter() }

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

        pokemonAdapter.setOnItemClickListener {
            val action =
                PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(it)
            findNavController().navigate(action)
        }

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.onQueryTextChange(query)
                binding.searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(nextText: String?): Boolean {
                viewModel.onQueryTextChange(nextText)
                return true
            }
        })
    }

    private fun observeLiveData() {
        binding.rvPokemon.adapter = pokemonAdapter
        viewModel.pokemonList.observe(viewLifecycleOwner) { pokemons ->
            pokemons.data?.let {
                binding.rvPokemon.visibility = View.VISIBLE
                pokemonAdapter.updatePokemonList(it)
                binding.searchView.clearFocus()
                if (it.isEmpty()) {
                    binding.tvErrorText.visibility = View.VISIBLE
                } else {
                    binding.tvErrorText.visibility = View.GONE
                }
            }
        }

        viewModel.pokemonError.observe(viewLifecycleOwner) { error ->
            error.data?.let {
                if (it) {
                    binding.tvErrorText.visibility = View.VISIBLE
                    binding.rvPokemon.visibility = View.GONE
                } else {
                    binding.tvErrorText.visibility = View.GONE
                }
            }
        }

        viewModel.pokemonLoading.observe(viewLifecycleOwner) { loading ->
            loading.data?.let {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvPokemon.visibility = View.GONE
                    binding.tvErrorText.visibility = View.GONE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }
}