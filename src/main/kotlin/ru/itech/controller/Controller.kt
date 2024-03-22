package ru.itech.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
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
import ru.itech.dto.TempDto
import ru.itech.service.CommunityService

/*
 Swagger аннотация доступна по ссылке:
 http://127.0.0.1:8080/swagger-ui/index.html#/
 */
@RestController
@RequestMapping("/api/v1")
@Tag(
    name = " RESTFul API",
    description = "сервис для игры в Тайного Санту",
)
class Controller (
        private val communityService: CommunityService,
    ){
    @Operation(method = "Получение всех данных со страницы")
    @GetMapping
    fun getAll(@RequestParam("page") pageIndex: Int) : List<CommunityDto> =
        communityService.getAll(pageIndex)

    @Operation(method = "Получение всех групп без участников")
    @GetMapping("/groups")
    fun getAllWithOutParticipants() : List<TempDto> =
        communityService.getAllWithOutParticipants()

    @Operation(method = "Получение по id")
    @GetMapping("/groups/{id}")
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

    @Operation(method = "Создать новую группу с пользователями")
    @PostMapping("/create")
    fun create(@RequestBody dto: CommunityDto): Int {
        return communityService.create(dto)
    }

    @Operation(method = "Создать новую учетную запись")
    @PostMapping("/group")
    fun groupcreate(@RequestBody dto: TempDto): Int {
        return communityService.groupcreate(dto)
    }

    @Operation(method = "Обновить учетную запись")
    @PutMapping("/group/{id}")
    @Parameter(description = "id - уникальный идентификатор пользователя или компании")
    fun update(@PathVariable id: Int, @RequestBody dto: TempDto) {
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