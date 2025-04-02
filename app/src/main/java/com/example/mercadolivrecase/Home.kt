package com.example.mercadolivrecase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.SearchView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mercadolivrecase.databinding.ActivityHomeBinding
import org.json.JSONObject

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var anuncioAdapter: AnuncioAdapter
    private lateinit var produtoAdapter: ProdutoAdapter
    private val listaAnuncios: MutableList<Anuncio> = mutableListOf()
    private val listaProdutosFiltrados: MutableList<Produto> = mutableListOf()
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)
        setupRecyclerViews()
        carregarAnuncios()
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
                    buscarProdutos(it)
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

    private fun buscarProdutos(termoBusca: String) {
        val url = "https://api.mercadolibre.com/sites/MLB/search?q=$termoBusca"
        val request = StringRequest(Request.Method.GET, url,
            { response ->
                listaProdutosFiltrados.clear()
                val jsonResponse = JSONObject(response)
                val results = jsonResponse.getJSONArray("results")
                for (i in 0 until results.length()) {
                    val item = results.getJSONObject(i)
                    val imagemUrl = item.getString("thumbnail")
                    val preco = "R$ " + item.getDouble("price")
                    val titulo = item.getString("title")
                    val condicao = if (item.getString("condition") == "new") "Novo" else "Usado"
                    listaProdutosFiltrados.add(Produto(imagemUrl, preco, titulo, condicao))
                }
                produtoAdapter.notifyDataSetChanged()
            },
            {
                Toast.makeText(this, "Erro ao buscar produtos", Toast.LENGTH_SHORT).show()
            })
        requestQueue.add(request)
    }
}
