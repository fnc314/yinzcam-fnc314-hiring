package com.yinzcam.fnc314.hiring.core.network

public sealed class NetworkResponse<out T, out E> {
  public data object Loading : NetworkResponse<Nothing, Nothing>()
  public data class Success<T>(
    val data: T
  ) : NetworkResponse<T, Nothing>()
  public sealed class Error<E> : NetworkResponse<Nothing, E>() {
    public data object NoNetwork : Error<Nothing>()
    public data class OtherError<T>(
      val throwable: T,
    ) : Error<Throwable>()
  }
}
