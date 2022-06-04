package ru.otus.client

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
open class CbrClientImpl : CbrClient {

    @Cacheable("cbrRates")
    override fun getRatesAsXml(url: String): String {

        val client = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        return response.body()
    }
}