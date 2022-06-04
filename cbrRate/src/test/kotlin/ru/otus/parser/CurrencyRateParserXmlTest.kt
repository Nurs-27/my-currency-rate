package ru.otus.parser

import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import ru.otus.domain.CurrencyRate

class CurrencyRateParserXmlTest {

    @Test
    fun parseTest() {
        //given
        val parser = CurrencyRateParserImpl()
        val uri = ClassLoader.getSystemResource("cbr_response_24-01-22.xml").toURI()
        val ratesXml = Files.readString(Paths.get(uri), Charset.forName("Windows-1251"))

        //when
        val rates = parser.parse(ratesXml)

        //then
        assertThat(rates.size).isEqualTo(34)
        assertThat(rates.contains(uSDrate)).isTrue
        assertThat(rates.contains(eURrate)).isTrue
        assertThat(rates.contains(jPYrate)).isTrue
    }

    val uSDrate = CurrencyRate(
        numCode = "840",
        charCode = "USD",
        nominal = "1",
        name = "Доллар США",
        value = "76,6903",
    )
    val eURrate = CurrencyRate(
        numCode = "978",
        charCode = "EUR",
        nominal = "1",
        name = "Евро",
        value = "86,9054",
    )
    val jPYrate = CurrencyRate(
        numCode = "392",
        charCode = "JPY",
        nominal = "100",
        name = "Японских иен",
        value = "67,3165",
    )
}