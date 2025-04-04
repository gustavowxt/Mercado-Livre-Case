package com.example.mercadolivredesafio.utils

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Success<out T>(val data: T) : ViewState<T>()
    data class Error(val message: String) : ViewState<Nothing>()
}