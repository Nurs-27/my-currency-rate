package ru.otus.parser

import ru.otus.domain.CurrencyRate

interface CurrencyRateParser {

    fun parse(ratesAsString: String): List<CurrencyRate>
}