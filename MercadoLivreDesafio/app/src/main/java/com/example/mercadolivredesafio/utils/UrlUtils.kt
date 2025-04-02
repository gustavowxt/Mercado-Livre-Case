package com.example.mercadolivredesafio.utils
import android.webkit.URLUtil

object UrlUtils {
    fun ensureHttps(url: String?): String? {
        return if (url.isNullOrBlank()) {
            null
        } else {
            if (URLUtil.isHttpsUrl(url)) {
                url
            } else if (URLUtil.isHttpUrl(url)) {
                url.replace("http://", "https://")
            } else {
                "https://$url"
            }
        }
    }
}