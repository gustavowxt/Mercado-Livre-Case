package com.example.mercadolivredesafio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductsAdapter(
    private val onItemClick: (Produto) -> Unit
) : ListAdapter<Produto, ProductsAdapter.ProdutoViewHolder>(ProdutoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val binding = ItemProdutoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProdutoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProdutoViewHolder(
        private val binding: ItemProdutoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(produto: Produto) {
            with(binding) {
                Glide.with(root.context)
                    .load(UrlUtils.ensureHttps(produto.thumbnail))
                    .into(imagemProduto)

                textoTitulo.text = produto.titulo
                textoPreco.text = CurrencyUtils.formatToBRL(produto.preco)

                if (produto.envio.freteGratis) {
                    textoFrete.visible()
                    textoFrete.text = root.context.getString(R.string.frete_gratis)
                } else {
                    textoFrete.gone()
                }

                root.setOnClickListener { onItemClick(produto) }
            }
        }
    }
}

class ProdutoDiffCallback : DiffUtil.ItemCallback<Produto>() {
    override fun areItemsTheSame(oldItem: Produto, newItem: Produto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Produto, newItem: Produto): Boolean {
        return oldItem == newItem
    }
}