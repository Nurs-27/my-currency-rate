package ru.otus.controller

import java.time.LocalDate
import mu.KLogging
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.otus.domain.CurrencyRate
import ru.otus.service.CurrencyRateService

@RestController
@RequestMapping(path = ["\${app.rest.api.prefix}/v1"])
class CurrencyRateController(
    private val service: CurrencyRateService,
) {

    @GetMapping("/currencyRate/{currency}/{date}")
    fun getCurrencyRate(
        @PathVariable("currency") currency: String,
        @DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("date") date: LocalDate
    ): CurrencyRate {
        logger.info("Getting currency rate for currency:{$currency}, date:{$date}")
        return service.getCurrencyRate(currency, date)
            .also { logger.info { "Rate is: {$it}" } }
    }

    private companion object: KLogging()
}