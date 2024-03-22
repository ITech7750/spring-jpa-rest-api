package ru.itech.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Информация о Temp")
data class TempDto(
    @Schema(description = "id компании")
    val id : Int? = null,
    @Schema(description = "Название компании")
    val name: String,
    @Schema(description = "Описание компании")
    val description: String,

)
