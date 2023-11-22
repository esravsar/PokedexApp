package esra.avsar.pokedexapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import esra.avsar.pokedexapp.databinding.AdapterPokemonItemBinding
import esra.avsar.pokedexapp.domain.model.Pokemon
import esra.avsar.pokedexapp.extension.capitalizeFirstChar
import esra.avsar.pokedexapp.extension.formatPokemonId

/**
 * Created by EsraAvsar on 20.11.2023.
 */
class PokemonListAdapter(
    private val pokemonList: ArrayList<Pokemon?>
) : RecyclerView.Adapter<PokemonListAdapter.PokemonHolder>() {

    class PokemonHolder(val binding: AdapterPokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val itemBinding =
            AdapterPokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        val pokemon = pokemonList.get(position)
        holder.binding.tvPokemonName.text = pokemon?.name?.capitalizeFirstChar()

        val pokemonId = pokemon?.url?.split("/").let { it?.get(it.size - 2)?.toIntOrNull() ?: 1 }

        val formattedPokemonId = pokemonId.formatPokemonId()
        holder.binding.tvPokemonNumber.text = formattedPokemonId

        val imageUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemonId}.png"
        Glide.with(holder.binding.ivPokemon.context)
            .load(imageUrl)
            .into(holder.binding.ivPokemon)

        holder.itemView.setOnClickListener {
            val action =
                PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(
                    pokemonId
                )
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int = pokemonList.size
}