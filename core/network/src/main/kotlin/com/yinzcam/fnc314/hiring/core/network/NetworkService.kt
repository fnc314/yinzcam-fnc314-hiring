package com.yinzcam.fnc314.hiring.core.network

import android.util.Log
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

public interface NetworkService {
  public suspend operator fun <T> invoke(
    request: Request,
    deserializer: DeserializationStrategy<T>,
  ): NetworkResponse<T, Any>

  public companion object {
    public val DEFAULT: NetworkService = object : NetworkService {
      private val client: OkHttpClient = OkHttpClient.Builder().apply {
        followRedirects(true)
        hostnameVerifier { _, _ -> true }
      }.build()
      private val engine = Json {
        ignoreUnknownKeys = true
        allowTrailingComma = true
        encodeDefaults = true
        prettyPrint = true
      }

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
            ).also {
              Log.i(
                NetworkService::class.simpleName,
                "Response is $resString"
              )
            }
          } ?: NetworkResponse.Error.NoNetwork
        }
      }
    }
  }
}