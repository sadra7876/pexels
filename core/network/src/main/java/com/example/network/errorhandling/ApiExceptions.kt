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

    data object UnKnownException:ApiExceptions(
        title = "UnKnown Error",
        message = "Something went wrong"
    ){
        private fun readResolve(): Any = UnKnownException
    }

    data object TimeoutException: ApiExceptions(
        title = "Timeout",
        message = "Request timed out"
    ) {
        private fun readResolve(): Any = TimeoutException
    }

    data object UnauthorizedException:ApiExceptions(
        title = "Unauthorized",
        message = "You are not authorized to perform this action"
    ){
        private fun readResolve(): Any = UnauthorizedException
    }

    data object RateLimitException:ApiExceptions(
        title = "Rate Limit Exceeded",
        message = "You have exceeded your rate limit"
    ){
        private fun readResolve(): Any = RateLimitException
    }

    data object ServerErrorException:ApiExceptions(
        title = "Server Error",
        message = "Something went wrong on our side"
    ){
        private fun readResolve(): Any = ServerErrorException
    }
}