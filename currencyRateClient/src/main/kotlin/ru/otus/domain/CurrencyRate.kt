package ru.otus.domain

data class CurrencyRate(
    val charCode: String,
    val nominal: String,
    val value: String,
)

enum class RateType(val serviceName: String) {
    CBR("cbr");

    companion object {
        private val mappingByServiceName = values()
            .associateBy { it.serviceName }

        fun getByServiceName(serviceName: String) =
            mappingByServiceName[serviceName.lowercase()] ?: throw RuntimeException()
    }
}