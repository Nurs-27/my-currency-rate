package ru.otus.client

import com.fasterxml.jackson.databind.ObjectMapper
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import mu.KLogging
import org.springframework.stereotype.Service
import ru.otus.config.CbrRateClientConfig
import ru.otus.domain.CurrencyRate

@Service("cbr")
class CbrRateClient(
    private val config: CbrRateClientConfig,
    private val httpClient: HttpClient,
    private val objectMapper: ObjectMapper,
) : RateClient {

    override fun getCurrencyRate(currency: String, date: LocalDate): CurrencyRate {
        logger.info("getCurrencyRate currency:{$currency}, date:{$date}")
        val urlWithParams = "${config.url}/$currency/${DATE_FORMATTER.format(date)}"
        try {
            val response = httpClient.performRequest(urlWithParams)
            return parse(response)
        } catch (ex: HttpClientException) {
            throw RateClientException("Error from Cbr Client host:" + ex.localizedMessage)
        } catch (ex: Exception) {
            logger.error("Getting currencyRate error, currency:{}, date:{}", currency, date, ex)
            throw RateClientException("Can't get currencyRate. currency:$currency, date:$date")
        }
    }

    private fun parse(rateAsString: String): CurrencyRate {
        return try {
            objectMapper.readValue(rateAsString, CurrencyRate::class.java)
        } catch (ex: Exception) {
            throw RateClientException("Can't parse string:$rateAsString")
        }
    }

    private companion object : KLogging() {
        const val DATE_FORMAT = "dd-MM-yyyy"
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT)
    }
}
