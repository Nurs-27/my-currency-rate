package ru.otus

import ru.otus.config.CbrConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(CbrConfig::class)
open class CbrRate

fun main(args: Array<String>) {
    runApplication<CbrRate>(*args)
}


fun train() {

    
}