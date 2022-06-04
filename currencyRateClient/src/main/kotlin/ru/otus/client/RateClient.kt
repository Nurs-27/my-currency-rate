package ru.otus.client

import java.time.LocalDate
import ru.otus.domain.CurrencyRate

interface RateClient {

    fun getCurrencyRate(currency: String, date: LocalDate): CurrencyRate
}