package ru.itech.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.jetbrains.annotations.NotNull

@Schema(description = "Информация о пользователе")
data class UserDto(
    @Schema(description = "Идентификатор пользователя")
    @NotNull
    val id : Int?,
    @Schema(description = "Имя пользователя")
    val name: String,
    val wish: String,
    var dependId: Int?,
)
