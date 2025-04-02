package com.example.mercadolivrecase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mercadolivrecase.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val produto = intent.getParcelableExtra<Produto>("produto") // Recebe o produto selecionado

        produto?.let {
            binding.imgProduto.setImageResource(it.imagem!!)
            binding.txtPreco.text = it.preco
            binding.txtDescricao.text = it.descricao
            binding.txtFrete.text = it.frete
        }
    }
}
