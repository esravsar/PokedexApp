package esra.avsar.pokedexapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import esra.avsar.pokedexapp.databinding.AdapterPokemonItemBinding
import esra.avsar.pokedexapp.domain.model.Pokemon

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
        holder.itemView.setOnClickListener {
            val action =
                PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(
                    pokemon?.name
                )
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.tvPokemonName.text =
            pokemon?.name?.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

        pokemonList.forEachIndexed { index, pokemon ->
            pokemon?.id = index + 1
        }

        val pokemonId = pokemon?.id

        pokemonId?.let {
            val formattedId = when {
                pokemonId < 10 -> "#00$pokemonId"
                pokemonId < 100 -> "#0$pokemonId"
                else -> "#$pokemonId"
            }
            holder.binding.tvPokemonNumber.text = formattedId
        }

        val url =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemon?.id}.png"
        Glide.with(holder.binding.ivPokemon.context)
            .load(url)
            .into(holder.binding.ivPokemon)
    }

    override fun getItemCount(): Int = pokemonList.size
}