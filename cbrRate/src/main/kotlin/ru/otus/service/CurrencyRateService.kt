package ru.otus.service

import ru.otus.client.CbrClient
import ru.otus.config.CbrConfig
import ru.otus.domain.CurrencyRate
import ru.otus.exception.CurrencyRateNotFoundException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.springframework.stereotype.Service
import ru.otus.parser.CurrencyRateParser

@Service
class CurrencyRateService(
    private val cbrClient: CbrClient,
    private val currencyRateParser: CurrencyRateParser,
    private val cbrConfig: CbrConfig
) {

    fun getCurrencyRate(currency: String, date: LocalDate): CurrencyRate {
        val urlWithParams = "${cbrConfig.url}?date_req=${DATE_FORMATTER.format(date)}"
        val ratesAsXml = cbrClient.getRatesAsXml(urlWithParams)
        val rates = currencyRateParser.parse(ratesAsXml)

        return rates
            .firstOrNull { it.charCode == currency }
            ?: throw CurrencyRateNotFoundException("Currency Rate not found. Currency:$currency, date:$date")
    }

    private companion object {
        const val DATE_FORMAT = "dd/MM/yyyy"
        val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    }
}