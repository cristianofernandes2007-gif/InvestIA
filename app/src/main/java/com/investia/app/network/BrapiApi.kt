package com.investia.app.network

import com.investia.app.model.BrapiResposta
import retrofit2.http.GET
import retrofit2.http.Path

interface BrapiApi {

    @GET("api/quote/{ticker}")
    suspend fun buscarCotacao(
        @Path("ticker") ticker: String
    ): BrapiResposta
}