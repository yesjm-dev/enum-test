package org.example.restdocsenumtest.docs

data class ApiResponseDto<T>(val data: T) {
    companion object {
        fun <T> of(data: T): ApiResponseDto<T> = ApiResponseDto(data)
    }
}