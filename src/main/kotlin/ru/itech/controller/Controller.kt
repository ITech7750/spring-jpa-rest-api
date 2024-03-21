package ru.itech.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.websocket.Decoder.Text
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.itech.dto.CommunityDto
import ru.itech.service.CommunityService

/*
 Swagger аннотация доступна по ссылке:
 http://127.0.0.1:8080/swagger-ui/index.html#/
 */
@RestController
@RequestMapping("/api/v1")
@Tag(
    name = "REST API",
    description = "Описание запросов",
)
class Controller (
        private val communityService: CommunityService,
    ){
    @Operation(method = "Получение всех данных со страницы")
    @GetMapping
    fun getAll(@RequestParam("page") pageIndex: Int) : List<CommunityDto> =
        communityService.getAll(pageIndex)

    @Operation(method = "Получение по id")
    @GetMapping("/{id}")
    @Parameter(description = "id - уникальный идентификатор пользователя или компании")
    fun getById(@PathVariable("id") id: Int): CommunityDto =
        communityService.getById(id)

    @Operation(method = "Поиск по странице")
    @GetMapping("/search")
    fun seach(@RequestParam("prefix") prefix: String): List<CommunityDto> =
        communityService.search(prefix)

    @Operation(method = "Вывести список завсимых объектов")
    @GetMapping("/titles")
    fun getCommunityTitle():List<String> =
        communityService.getCommunityTitle()

    @Operation(method = "Создать новую учетную запись")
    @PostMapping("/create_community")
    fun create(@RequestBody dto: CommunityDto): Int {
        return communityService.create(dto)
    }

    @Operation(method = "Обновить учетную запись")
    @PutMapping("/{id}")
    @Parameter(description = "id - уникальный идентификатор пользователя или компании")
    fun update(@PathVariable id: Int, @RequestBody dto: CommunityDto) {
        return communityService.update(id,dto)
    }

    @Operation(method = "Удалить учетную запись")
    @DeleteMapping("/{id}")
    @Parameter(description = "id - уникальный идентификатор пользователя или компании")
    fun delete(@PathVariable id: Int){
        return communityService.delete(id)

    }

    /*
    Скрыть аннотацию к методу можно при помощи
    @Parameter(hidden = true)
     */

}