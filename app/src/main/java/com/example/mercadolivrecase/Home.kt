package com.example.mercadolivrecase

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mercadolivrecase.databinding.ActivityHomeBinding


class Home: AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var anuncioAdapter: AnuncioAdapter
    private lateinit var produtoAdapter: ProdutoAdapter
    private val listaAnuncios: MutableList<Anuncio> = mutableListOf()
    private val listaProdutos: MutableList<Produto> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewAnuncios = binding.recyclerViewAnuncios
        recyclerViewAnuncios.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewAnuncios.setHasFixedSize(true)
        anuncioAdapter = AnuncioAdapter(this, listaAnuncios)
        recyclerViewAnuncios.adapter = anuncioAdapter
        getAnuncios()

        val recyclerViewProdutos = binding.recyclerViewProdutos
        recyclerViewProdutos.layoutManager = GridLayoutManager(this, 2)
        recyclerViewProdutos.setHasFixedSize(true)
        produtoAdapter = ProdutoAdapter(this, listaProdutos)
        recyclerViewProdutos.adapter = produtoAdapter
        getProdutos()

    }

    private fun getAnuncios(){
        val anuncio1 = Anuncio(R.drawable.arte1)
        listaAnuncios.add(anuncio1)

        val anuncio2 = Anuncio(R.drawable.arte2)
        listaAnuncios.add(anuncio2)

        val anuncio3 = Anuncio(R.drawable.arte3)
        listaAnuncios.add(anuncio3)
    }

    private fun getProdutos(){
        val produto1 = Produto(
            R.drawable.produto1,
            "R$ 7.499,90",
            "ACER Notebook Nitro 5, AMD R7 4800H, 8GB, 512GB SDD",
            "Frete grátis"
        )
        listaProdutos.add(produto1)

        val produto2 = Produto(
            R.drawable.produto2,
            "R$ 4.388,01",
            "Nike Air Jordan 1 Chicago 1994 Sample",
            "Frete grátis"
        )
        listaProdutos.add(produto2)

        val produto3 = Produto(
            R.drawable.produto3,
            "R$ 2.499,90",
            "Microsoft Xbox One - 1TB Project Scorpio Edition",
            "Frete grátis"
        )
        listaProdutos.add(produto3)

        val produto4 = Produto(
            R.drawable.produto4,
            "R$ 1.899,90",
            "Smart TV LG 60' 4K UHD 60UQ8050 WiFi",
            "Frete grátis"
        )
        listaProdutos.add(produto4)

    }
}