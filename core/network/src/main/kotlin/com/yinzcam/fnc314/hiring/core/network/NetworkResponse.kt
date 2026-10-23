package com.yinzcam.fnc314.hiring.core.network

public sealed class NetworkResponse<out T, out E> {
  data object Loading : NetworkResponse<Nothing, Nothing>()
  data class Success<T>(
    val data: T
  ) : NetworkResponse<T, Nothing>()
  sealed class Error<E> : NetworkResponse<Nothing, E>() {
    data object NoNetwork : Error<Nothing>()
    data class OtherError<T>(
      val throwable: T,
    ) : Error<Throwable>()
  }
}
