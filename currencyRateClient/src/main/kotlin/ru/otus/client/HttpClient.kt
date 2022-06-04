package ru.otus.client

interface HttpClient {

    fun performRequest(url: String): String
}