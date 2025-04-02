package com.example.mercadolivredesafio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class AtributosProdutoAdapter :
    ListAdapter<Atributo, AtributosProdutoAdapter.AtributoViewHolder>(AtributoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtributoViewHolder {
        val binding = ItemAtributoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AtributoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AtributoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AtributoViewHolder(
        private val binding: ItemAtributoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(atributo: Atributo) {
            with(binding) {
                textoNomeAtributo.text = atributo.nome
                textoValorAtributo.text = atributo.valorNome
            }
        }
    }
}

class AtributoDiffCallback : DiffUtil.ItemCallback<Atributo>() {
    override fun areItemsTheSame(oldItem: Atributo, newItem: Atributo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Atributo, newItem: Atributo): Boolean {
        return oldItem == newItem
    }
}