package com.yinzcam.fnc314.hiring.core.network

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

public interface NetworkService {
  suspend operator fun <T> invoke(
    request: Request,
    deserializer: DeserializationStrategy<T>,
  ): NetworkResponse<T, Any>

  companion object {
    public val DEFAULT: NetworkService = object : NetworkService {
      private val client: OkHttpClient = OkHttpClient.Builder().apply {
        followRedirects(true)
      }.build()
      private val engine = Json { ignoreUnknownKeys = true }

      override suspend fun <T> invoke(
        request: Request,
        deserializer: DeserializationStrategy<T>,
      ): NetworkResponse<T, Any> {
        return client.newCall(request).execute().use { response ->
          response.body?.string()?.let { resString ->
            NetworkResponse.Success(
              data = engine.decodeFromString(
                deserializer = deserializer,
                string = resString
              )
            )
          } ?: NetworkResponse.Error.NoNetwork
        }
      }
    }
  }
}