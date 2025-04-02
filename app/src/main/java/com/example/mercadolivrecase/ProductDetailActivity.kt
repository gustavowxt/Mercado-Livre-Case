package com.example.mercadolivrecase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mercadolivrecase.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val produto = intent.getParcelableExtra<Produto>("produto") // Recebe o produto selecionado

        produto?.let {
            Glide.with(this)
                .load(it.imagemUrl) // Carrega a imagem da URL
                .into(binding.imgProduto) // Define no ImageView

            binding.txtPreco.text = it.preco
            binding.txtDescricao.text = it.descricao
            binding.txtFrete.text = it.condicao
        }
    }
}

