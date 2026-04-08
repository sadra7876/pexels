package com.example.network.errorhandling

sealed class ApiExceptions(
    val title: String,
    override val message: String
): RuntimeException() {
    data object NotFoundException: ApiExceptions(
        title = "Not Found",
        message = "The requested resource was not found"
    ) {
        private fun readResolve(): Any = NotFoundException
    }

    data object ConnectionError: ApiExceptions(
        title = "Connection Error",
        message = "Please check your internet connection"
    ) {
        private fun readResolve(): Any = ConnectionError
    }

}