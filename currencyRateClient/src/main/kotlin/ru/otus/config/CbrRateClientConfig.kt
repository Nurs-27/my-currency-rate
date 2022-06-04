package ru.otus.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "cbr-rate-client")
class CbrRateClientConfig(
    val url: String,
)