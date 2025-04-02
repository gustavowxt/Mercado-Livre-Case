package com.example.mercadolivredesafio.di

import com.example.mercadolivredesafio.data.MercadoLivreApi
import com.example.mercadolivredesafio.data.repository.ProdutoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun fornecerMercadoLivreApi(): MercadoLivreApi {
        return Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    })
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build()
            )
            .build()
            .create(MercadoLivreApi::class.java)
    }

    @Provides
    @Singleton
    fun fornecerProdutoRepository(api: MercadoLivreApi): ProdutoRepository {
        return ProdutoRepository(api)
    }
}