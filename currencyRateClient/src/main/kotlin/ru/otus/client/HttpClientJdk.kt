package ru.otus.client

import java.net.URI
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class HttpClientJdk : HttpClient {

    override fun performRequest(url: String): String {
        logger.info { "Getting http request, url:{$url}" }
        try {
            val client = java.net.http.HttpClient.newHttpClient()
            val request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())

            return response.body()
        } catch (ex: Exception) {
            throw HttpClientException(ex.localizedMessage)
                .also { logger.error { "Http error occurred during for url: {$url}. $ex" } }
        }
    }

    private companion object : KLogging()
}