package com.example.mercadolivredesafio.utils

import java.text.NumberFormat
import java.util.*

object CurrencyUtils {
    private val ptBrLocale = Locale("pt", "BR")

    fun formatToBRL(value: Double): String {
        return NumberFormat.getCurrencyInstance(ptBrLocale).format(value)
    }

    fun formatToBRL(value: Int): String {
        return NumberFormat.getCurrencyInstance(ptBrLocale).format(value)
    }
}