package com.investia.app

import android.content.Context

object FavoritesStore {

    private const val ARQUIVO = "investia_favoritos"
    private const val CHAVE = "tickers"

    fun carregar(context: Context): Set<String> {
        val preferences = context.getSharedPreferences(
            ARQUIVO,
            Context.MODE_PRIVATE
        )

        return preferences.getStringSet(
            CHAVE,
            emptySet()
        ) ?: emptySet()
    }

    fun salvar(
        context: Context,
        favoritos: Set<String>
    ) {
        val preferences = context.getSharedPreferences(
            ARQUIVO,
            Context.MODE_PRIVATE
        )

        preferences.edit()
            .putStringSet(
                CHAVE,
                favoritos
            )
            .apply()
    }
}