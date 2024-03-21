package ru.itech.exception

// Служебный класс с основной информацией об ошибках
data class ApiError(
    val errorCode: Int,
    val description: String
)
