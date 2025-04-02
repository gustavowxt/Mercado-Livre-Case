package com.example.mercadolivredesafio.data

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MercadoLivreApi {
    @GET("sites/MLA/search")
    suspend fun pesquisarProdutos(
        @Query("q") query: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 50
    ): Response<RespostaPesquisa>

    @GET("items/{itemId}")
    suspend fun obterDetalhesProduto(
        @Path("itemId") itemId: String
    ): Response<Produto>

    @GET("items")
    suspend fun obterMultiplosProdutos(
        @Query("ids") itemIds: String,
        @Query("attributes") attributes: String? = null
    ): Response<List<Produto>>
}