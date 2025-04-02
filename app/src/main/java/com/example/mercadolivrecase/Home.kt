package com.example.mercadolivrecase

import android.app.Activity
import android.content.Intent
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

        setupRecyclerViews()
        carregarAnuncios()
        carregarProdutos()
        setupSearchView()
    }

    private fun setupRecyclerViews() {
        // RecyclerView para anúncios
        binding.recyclerViewAnuncios.apply {
            layoutManager = LinearLayoutManager(this@Home, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            anuncioAdapter = AnuncioAdapter(this@Home, listaAnuncios)
            adapter = anuncioAdapter
        }

        // RecyclerView para produtos
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
                    val intent = Intent(this@Home, SearchResultsActivity::class.java)
                    intent.putExtra("query", it)
                    intent.putParcelableArrayListExtra("produtos", ArrayList(listaProdutos))
                    startActivity(intent)
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
            add(Produto(R.drawable.produto1, "R$ 7.499,90", "ACER Notebook Nitro 5, AMD R7 4800H, 8GB, 512GB SDD", "Frete grátis"))
            add(Produto(R.drawable.produto2, "R$ 4.388,01", "Nike Air Jordan 1 Chicago 1994 Sample", "Frete grátis"))
            add(Produto(R.drawable.produto3, "R$ 2.499,90", "Microsoft Xbox One - 1TB Project Scorpio Edition", "Frete grátis"))
            add(Produto(R.drawable.produto4, "R$ 1.899,90", "Smart TV LG 60' 4K UHD 60UQ8050 WiFi", "Frete grátis"))
        }

        listaProdutosFiltrados.addAll(listaProdutos)
        produtoAdapter.notifyDataSetChanged()
    }
}
