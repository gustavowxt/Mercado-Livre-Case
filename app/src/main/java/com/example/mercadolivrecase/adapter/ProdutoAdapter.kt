package com.example.mercadolivrecase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mercadolivrecase.databinding.ProdutoItemBinding


class ProdutoAdapter(private val context: Context, private val produtos: List<Produto>) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.produto_item, parent, false)
        return ProdutoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = produtos[position]

        // Carregar imagem da URL com Glide
        Glide.with(context)
            .load(produto.imagemUrl)
            .placeholder(R.drawable.placeholder) // Imagem temporária enquanto carrega
            .error(R.drawable.error_image) // Imagem de erro caso falhe
            .into(holder.imagemProduto)

        holder.titulo.text = produto.descricao
        holder.preco.text = produto.preco
        holder.condicao.text = produto.condicao
    }

    override fun getItemCount(): Int = produtos.size

    class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagemProduto: ImageView = itemView.findViewById(R.id.imagemProduto)
        val titulo: TextView = itemView.findViewById(R.id.tituloProduto)
        val preco: TextView = itemView.findViewById(R.id.precoProduto)
        val condicao: TextView = itemView.findViewById(R.id.condicaoProduto)
    }
}


