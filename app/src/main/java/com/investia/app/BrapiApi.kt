package com.investia.app

import retrofit2.http.GET
import retrofit2.http.Path

interface BrapiApi {

    @GET("api/quote/{ticker}")
    suspend fun buscarCotacao(
        @Path("ticker") ticker: String
    ): BrapiResposta
}