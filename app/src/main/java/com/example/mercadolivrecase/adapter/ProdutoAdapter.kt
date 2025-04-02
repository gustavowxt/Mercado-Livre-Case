package com.example.mercadolivrecase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolivrecase.databinding.ProdutoItemBinding


class ProdutoAdapter(private val context: Context, val listaProdutos: MutableList<Produto>) :
    RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val itemLista = ProdutoItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProdutoViewHolder(itemLista)
    }

    override fun getItemCount() = listaProdutos.size

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = listaProdutos[position]
        holder.imgProduto.setImageResource(produto.imagem!!)
        holder.precoProduto.text = produto.preco
        holder.descricaoProduto.text = produto.descricao
        holder.frete.text = produto.frete

        holder.itemView.setOnClickListener {
            // Ao clicar no item, abre a tela de detalhes do produto
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("produto", produto) // Envia o produto para a tela de detalhes
            context.startActivity(intent)
        }
    }

    inner class ProdutoViewHolder(binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgProduto = binding.img
        val precoProduto = binding.txtPreco
        val descricaoProduto = binding.txtDescricao
        val frete = binding.txtFrete
    }
}
