package ru.itech.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Информация о компании")
data class CommunityDto(
        @Schema(description = "id компании")
        val id : Int? = null,
        @Schema(description = "Название компании")
        val title: String,
        @Schema(description = "Описание компании")
        val description: String,
        @Schema(description = "Члены компании")
        val members: List<UserDto>,
)

