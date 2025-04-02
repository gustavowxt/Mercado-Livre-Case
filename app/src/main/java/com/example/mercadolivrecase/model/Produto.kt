package com.example.mercadolivrecase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Produto(
    val imagemUrl: String,
    val preco: String,
    val descricao: String,
    val condicao: String
) : Parcelable // Implementa Parcelable corretamente
