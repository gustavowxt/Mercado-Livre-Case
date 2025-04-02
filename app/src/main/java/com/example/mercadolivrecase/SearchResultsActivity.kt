package com.example.mercadolivrecase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mercadolivrecase.databinding.ActivitySearchResultsBinding

class SearchResultsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchResultsBinding
    private lateinit var produtoAdapter: ProdutoAdapter
    private val listaProdutos: MutableList<Produto> = mutableListOf()
    private val listaProdutosFiltrados: MutableList<Produto> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        listaProdutos.addAll(intent.getParcelableArrayListExtra("produtos") ?: emptyList())

        val query = intent.getStringExtra("query")
        query?.let { filterProducts(it) }


    }

    private fun setupRecyclerView() {
        binding.recyclerViewProdutos.apply {
            layoutManager = LinearLayoutManager(this@SearchResultsActivity)
            setHasFixedSize(true)
            produtoAdapter = ProdutoAdapter(this@SearchResultsActivity, listaProdutosFiltrados)
            adapter = produtoAdapter
        }
    }

    private fun filterProducts(query: String) {
        listaProdutosFiltrados.clear()
        listaProdutosFiltrados.addAll(listaProdutos.filter { produto ->
            produto.descricao?.contains(query, ignoreCase = true) == true
        })
        produtoAdapter.notifyDataSetChanged()
    }
}
