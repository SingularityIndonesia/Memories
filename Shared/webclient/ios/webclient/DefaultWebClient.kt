package webclient

import core.context.WebClient
import core.lift
import core.operation.SystemResult
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.readBytes
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path

actual fun defaultWebClient(
    host: String,
    basePath: String,
): WebClient =
    object : WebClient {
        override val host: String = host

        override val basePath: String = basePath

        val ktorHttpClient by lazy {
            HttpClient {
                defaultRequest {
                    url {
                        protocol = URLProtocol.HTTPS
                        this.host = host
                        path(basePath)
                    }
                }
            }
        }

        override suspend fun post(
            endpoint: String?,
            headers: Map<String, String>?,
            params: Map<String, String>?,
            body: String?,
        ): SystemResult<suspend () -> ByteArray> =
            runCatching {
                val requestBuilder: HttpRequestBuilder.() -> Unit = {
                    if (!headers.isNullOrEmpty()) {
                        headers.forEach {
                            header(it.key, it.value)
                        }
                    }

                    if (!params.isNullOrEmpty()) {
                        params.forEach {
                            parameter(it.key, it.value)
                        }
                    }

                    if (!body.isNullOrBlank()) {
                        contentType(ContentType.Application.Json)
                        setBody(body)
                    }
                }

                val response =
                    if (endpoint.isNullOrBlank()) {
                        ktorHttpClient.post(requestBuilder)
                    } else {
                        ktorHttpClient.post(endpoint, requestBuilder)
                    }
                suspend { response.readBytes() }
            }.lift()

        override suspend fun get(
            endpoint: String?,
            headers: Map<String, String>?,
            params: Map<String, String>?,
        ): SystemResult<suspend () -> ByteArray> =
            kotlin
                .runCatching {
                    val requestBuilder: HttpRequestBuilder.() -> Unit = {
                        if (!headers.isNullOrEmpty()) {
                            headers.forEach {
                                header(it.key, it.value)
                            }
                        }

                        if (!params.isNullOrEmpty()) {
                            params.forEach {
                                parameter(it.key, it.value)
                            }
                        }
                    }

                    val response =
                        if (endpoint.isNullOrBlank()) {
                            ktorHttpClient.get(requestBuilder)
                        } else {
                            ktorHttpClient.get(endpoint, requestBuilder)
                        }

                    suspend { response.readBytes() }
                }.lift()

        override suspend fun put(
            endpoint: String?,
            headers: Map<String, String>?,
            params: Map<String, String>?,
            body: String?,
        ): SystemResult<suspend () -> ByteArray> =
            runCatching {
                val requestBuilder: HttpRequestBuilder.() -> Unit = {
                    if (!headers.isNullOrEmpty()) {
                        headers.forEach {
                            header(it.key, it.value)
                        }
                    }

                    if (!params.isNullOrEmpty()) {
                        params.forEach {
                            parameter(it.key, it.value)
                        }
                    }

                    if (!body.isNullOrBlank()) {
                        contentType(ContentType.Application.Json)
                        setBody(body)
                    }
                }

                val response =
                    if (endpoint.isNullOrBlank()) {
                        ktorHttpClient.put(requestBuilder)
                    } else {
                        ktorHttpClient.put(endpoint, requestBuilder)
                    }

                suspend { response.readBytes() }
            }.lift()
    }
