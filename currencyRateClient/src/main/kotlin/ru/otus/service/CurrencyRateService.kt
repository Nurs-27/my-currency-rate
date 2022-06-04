package ru.otus.service

import java.time.LocalDate
import mu.KLogging
import org.springframework.stereotype.Service
import ru.otus.client.RateClient
import ru.otus.domain.CurrencyRate
import ru.otus.domain.RateType

@Service
class CurrencyRateService(
    private val clients: Map<String, RateClient>
) {

    fun getCurrencyRate(rateType: RateType, currency: String, date: LocalDate): CurrencyRate {
        logger.info("Getting currency rate for rateType:{$rateType}, currency:{$currency}, date:{$date}")
        val client = clients.getValue(rateType.serviceName)
        return client.getCurrencyRate(currency, date)
    }

    private companion object : KLogging()
}