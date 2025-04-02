package com.example.mercadolivrecase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.privacysandbox.tools.core.model.Method
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mercadolivrecase.databinding.ActivitySearchResultsBinding
import org.json.JSONObject
import com.android.volley.Request



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

        val query = intent.getStringExtra("query")
        query?.let {
            fetchProdutosFromApi(it)
        }


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


    private fun fetchProdutosFromApi(query: String) {
        val url = "https://api.mercadolibre.com/sites/MLB/search?q=$query"

        val request = StringRequest(Request.Method.GET, url, { response ->
            val jsonObject = JSONObject(response)
            val resultsArray = jsonObject.getJSONArray("results")

            listaProdutosFiltrados.clear()

            for (i in 0 until resultsArray.length()) {
                val item = resultsArray.getJSONObject(i)
                val titulo = item.getString("title")
                val preco = "R$ " + item.getDouble("price").toString()
                val imagemUrl = item.getString("thumbnail")
                val condicao = item.getString("condition").replace("new", "Novo").replace("used", "Usado")

                listaProdutosFiltrados.add(Produto( imagemUrl, preco, titulo, condicao))
            }

            produtoAdapter.notifyDataSetChanged()

        }, { error ->
            Log.e("API_ERROR", "Erro ao buscar produtos: ${error.message}")
        })

        Volley.newRequestQueue(this).add(request)
    }


}
