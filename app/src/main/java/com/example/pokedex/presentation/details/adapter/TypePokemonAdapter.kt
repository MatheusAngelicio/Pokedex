package com.example.pokedex.presentation.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.data.model.Types
import com.example.pokedex.databinding.TypeListPokemonBinding
import com.example.pokedex.presentation.base.BaseRecyclerAdapter
import com.example.pokedex.util.getTypePokemon

class TypePokemonAdapter : BaseRecyclerAdapter<Types, TypePokemonAdapter.TypePokemonViewHolder>() {

    override fun onBindViewHolder(viewHolder: TypePokemonViewHolder, position: Int) {
        viewHolder.bind(mData[position])
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TypePokemonViewHolder {
        val binding =
            TypeListPokemonBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return TypePokemonViewHolder(binding)
    }

    inner class TypePokemonViewHolder(binding: TypeListPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val typePokemon = binding.typeRecyclerImage

        fun bind(types: Types) {
            typePokemon.setImageResource(getTypePokemon(types.type?.name ?: "normal"))

        }
    }

}
