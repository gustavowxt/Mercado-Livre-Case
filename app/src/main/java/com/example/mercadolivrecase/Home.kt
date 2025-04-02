package com.example.mercadolivrecase

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
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

        setupRecyclerViews()
        carregarAnuncios()
        carregarProdutos()
        setupSearchView()
    }

    private fun setupRecyclerViews() {
        binding.recyclerViewAnuncios.apply {
            layoutManager = LinearLayoutManager(this@Home, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            anuncioAdapter = AnuncioAdapter(this@Home, listaAnuncios)
            adapter = anuncioAdapter
        }

        binding.recyclerViewProdutos.apply {
            layoutManager = GridLayoutManager(this@Home, 2)
            setHasFixedSize(true)
            produtoAdapter = ProdutoAdapter(this@Home, listaProdutosFiltrados)
            adapter = produtoAdapter
        }
    }

    private fun setupSearchView() {
        binding.pesquisa.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    val produtosFiltrados = listaProdutos.filter { produto ->
                        produto.descricao.contains(query, ignoreCase = true)
                    }
                    listaProdutosFiltrados.clear()
                    listaProdutosFiltrados.addAll(produtosFiltrados)
                    produtoAdapter.notifyDataSetChanged()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun carregarAnuncios() {
        listaAnuncios.apply {
            add(Anuncio(R.drawable.arte1))
            add(Anuncio(R.drawable.arte2))
            add(Anuncio(R.drawable.arte3))
        }
        anuncioAdapter.notifyDataSetChanged()
    }

    private fun carregarProdutos() {
        listaProdutos.apply {
            add(Produto("https://exemplo.com/produto1.jpg", "R$ 7.499,90", "ACER Notebook Nitro 5, AMD R7 4800H, 8GB, 512GB SDD", "Novo"))
            add(Produto("https://exemplo.com/produto2.jpg", "R$ 4.388,01", "Nike Air Jordan 1 Chicago 1994 Sample", "Usado"))
            add(Produto("https://exemplo.com/produto3.jpg", "R$ 2.499,90", "Microsoft Xbox One - 1TB Project Scorpio Edition", "Usado"))
            add(Produto("https://exemplo.com/produto4.jpg", "R$ 1.899,90", "Smart TV LG 60' 4K UHD 60UQ8050 WiFi", "Novo"))
        }

        listaProdutosFiltrados.addAll(listaProdutos)
        produtoAdapter.notifyDataSetChanged()
    }
}