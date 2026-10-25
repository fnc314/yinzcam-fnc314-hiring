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
    public val jsonInstance: Json = Json {
      ignoreUnknownKeys = true
      allowTrailingComma = true
      encodeDefaults = true
      prettyPrint = true
    }
    public val okHttpClient: OkHttpClient = OkHttpClient.Builder().apply {
      followRedirects(true)
      hostnameVerifier { _, _ -> true }
    }.build()
    public val DEFAULT: NetworkService = object : NetworkService {
      override suspend fun <T> invoke(
        request: Request,
        deserializer: DeserializationStrategy<T>,
      ): NetworkResponse<T, Any> {
        return okHttpClient.newCall(request).execute().use { response ->
          response.body?.string()?.let { resString ->
            NetworkResponse.Success(
              data = jsonInstance.decodeFromString(
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