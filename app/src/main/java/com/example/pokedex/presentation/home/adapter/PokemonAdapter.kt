package com.example.pokedex.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.data.model.Results
import com.example.pokedex.databinding.PokemonListBinding
import com.example.pokedex.presentation.base.BaseRecyclerAdapter
import com.example.pokedex.util.SetupImageGlide
import com.example.pokedex.util.formattedNumber

class PokemonAdapter : BaseRecyclerAdapter<Results, PokemonAdapter.PokemonViewHolder>() {

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(mData[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = PokemonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding, parent.context)
    }


    inner class PokemonViewHolder(private val binding: PokemonListBinding, var context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Results, position: Int) {
            binding.apply {
                pokemon.number = position + 1
                pokemon.imageUrl = formattedNumber(pokemon.number)
                namePokemon.text = pokemon.name
                SetupImageGlide.setImageUrl(pokemon.imageUrl, imagePokemon, imageProgress, context)
                container.setOnClickListener { onItemClickListener?.invoke(pokemon) }
            }
        }
    }
}