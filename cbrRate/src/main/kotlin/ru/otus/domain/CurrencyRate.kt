package ru.otus.domain

data class CurrencyRate(
    val numCode: String,
    val charCode: String,
    val nominal: String,
    val name: String,
    val value: String,
)