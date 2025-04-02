package com.example.mercadolivrecase

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mercadolivrecase.databinding.ActivityHomeBinding


class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var anuncioAdapter: AnuncioAdapter
    private lateinit var produtoAdapter: ProdutoAdapter
    private val listaAnuncios: MutableList<Anuncio> = mutableListOf()
    private val listaProdutos: MutableList<Produto> = mutableListOf()
    private val listaProdutosFiltrados: MutableList<Produto> = mutableListOf()

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
        produtoAdapter = ProdutoAdapter(this, listaProdutosFiltrados)
        recyclerViewProdutos.adapter = produtoAdapter
        getProdutos()

        setupSearchView()
    }

    private fun setupSearchView() {
        val searchView = binding.pesquisa

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterProducts(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterProducts(newText)
                return true
            }
        })
    }

    private fun filterProducts(query: String?) {
        listaProdutosFiltrados.clear()
        if (query.isNullOrBlank()) {
            listaProdutosFiltrados.addAll(listaProdutos)
        } else {
            listaProdutosFiltrados.addAll(listaProdutos.filter { produto ->
                produto.descricao?.contains(query, ignoreCase = true) == true
            })
        }
        produtoAdapter.notifyDataSetChanged()
    }

    private fun getAnuncios() {
        listaAnuncios.add(Anuncio(R.drawable.arte1))
        listaAnuncios.add(Anuncio(R.drawable.arte2))
        listaAnuncios.add(Anuncio(R.drawable.arte3))
    }

    private fun getProdutos() {
        listaProdutos.add(
            Produto(R.drawable.produto1, "R$ 7.499,90", "ACER Notebook Nitro 5, AMD R7 4800H, 8GB, 512GB SDD", "Frete grátis")
        )
        listaProdutos.add(
            Produto(R.drawable.produto2, "R$ 4.388,01", "Nike Air Jordan 1 Chicago 1994 Sample", "Frete grátis")
        )
        listaProdutos.add(
            Produto(R.drawable.produto3, "R$ 2.499,90", "Microsoft Xbox One - 1TB Project Scorpio Edition", "Frete grátis")
        )
        listaProdutos.add(
            Produto(R.drawable.produto4, "R$ 1.899,90", "Smart TV LG 60' 4K UHD 60UQ8050 WiFi", "Frete grátis")
        )

        listaProdutosFiltrados.addAll(listaProdutos)
    }
}
