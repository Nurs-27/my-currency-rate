package ru.otus.client

interface CbrClient {

    fun getRatesAsXml(url: String): String
}