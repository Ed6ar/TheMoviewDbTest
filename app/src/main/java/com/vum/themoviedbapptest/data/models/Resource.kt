package com.vum.themoviedbapptest.data.models

import androidx.annotation.StringRes

sealed class Resource<T>(
    val data: T?,
    val message: String?,
    val int: Int?
) {
    class Success<T>(data: T?): Resource<T>(data, null, null)
    class Error<T>(data: T?, message: String?): Resource<T>(data, message, null)
    class Exception<T>(@StringRes message: Int): Resource<T>(null, null, message)
}
