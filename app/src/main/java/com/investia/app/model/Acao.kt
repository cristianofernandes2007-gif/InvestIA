package com.investia.app.model

data class Acao(
    val symbol: String,
    val longName: String?,
    val regularMarketPrice: Double?,
    val regularMarketChange: Double?,
    val regularMarketChangePercent: Double?,
    val regularMarketOpen: Double?,
    val regularMarketDayHigh: Double?,
    val regularMarketDayLow: Double?,
    val regularMarketPreviousClose: Double?,
    val regularMarketVolume: Double?
)