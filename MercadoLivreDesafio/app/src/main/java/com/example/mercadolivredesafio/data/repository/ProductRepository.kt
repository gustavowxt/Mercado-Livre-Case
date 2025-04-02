package com.example.mercadolivredesafio.data.repository

import com.bumptech.glide.load.engine.Resource
import com.example.mercadolivredesafio.data.MercadoLivreApi
import javax.inject.Inject

class ProdutoRepository @Inject constructor(
    private val api: MercadoLivreApi
) {
    suspend fun pesquisarProdutos(query: String): Resource<RespostaPesquisa> {
        return try {
            val response = api.pesquisarProdutos(query)
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Erro: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Erro de rede: ${e.message}")
        }
    }

    suspend fun obterDetalhesProduto(itemId: String): Resource<Produto> {
        return try {
            val response = api.obterDetalhesProduto(itemId)
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Erro: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Erro de rede: ${e.message}")
        }
    }
}