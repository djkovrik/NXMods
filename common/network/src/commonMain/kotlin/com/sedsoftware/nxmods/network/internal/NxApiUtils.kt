package com.sedsoftware.nxmods.network.internal

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.coroutinesinterop.completableFromCoroutine
import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.single.Single
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType

internal inline fun <reified T> NxModsSharedApi.doGet(url: String, key: String = apiKey): Single<T> =
    singleFromCoroutine {
        httpClient
            .get(url) {
                headers {
                    append(HttpHeaders.Accept, "*/*")
                    append(HttpHeaders.UserAgent, NxModsSharedApi.USER_AGENT)
                    append(NxModsSharedApi.API_KEY_HEADER, key)
                }
            }
            .body()
    }

internal inline fun NxModsSharedApi.doPost(url: String, body: Any): Completable =
    completableFromCoroutine {
        httpClient
            .post(url) {
                headers {
                    append(HttpHeaders.Accept, "*/*")
                    append(HttpHeaders.UserAgent, NxModsSharedApi.USER_AGENT)
                    append(NxModsSharedApi.API_KEY_HEADER, apiKey)
                }
                contentType(Application.Json)
                setBody(body)
            }
    }

internal inline fun NxModsSharedApi.doDelete(url: String, body: Any): Completable =
    completableFromCoroutine {
        httpClient
            .delete(url) {
                headers {
                    append(HttpHeaders.Accept, "*/*")
                    append(HttpHeaders.UserAgent, NxModsSharedApi.USER_AGENT)
                    append(NxModsSharedApi.API_KEY_HEADER, apiKey)
                }
                contentType(Application.Json)
                setBody(body)
            }
    }
