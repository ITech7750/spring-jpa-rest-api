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
import ru.itech.dto.UserDto
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

    @Operation(method = "Получение всех групп без данных об участниках")
    @GetMapping("/groups")
    fun getAllWithOutParticipants() : List<TempDto> =
        communityService.getAllWithOutParticipants()

    @Operation(method = "Получение информации о группе по id")
    @GetMapping("/groups/{id}")
    @Parameter(description = "id - уникальный идентификатор пользователя или компании")
    fun getById(@PathVariable("id") id: Int): CommunityDto =
        communityService.getById(id)


    @Operation(method = "Поиск по странице")
    @GetMapping("/search")
    fun seach(@RequestParam("prefix") prefix: String): List<CommunityDto> =
        communityService.search(prefix)

    @Operation(method = "Вывести список участников сообщества")
    @GetMapping("/groups/title")
    fun getCommunityTitle():List<String> =
        communityService.getCommunityTitle()

    @Operation(method = "Вывести информацию об участнике сообщества")
    @GetMapping("/grous/title/{id}")
    fun getInfAbout(@PathVariable("id") id: Int):List<String> =
        communityService.getCommunityTitle()


    @Operation(method = "Получение информации для конкретного участника группы, кому он дарит подарок")
    @GetMapping("/group/{companyId}/participant/{userId}/recipient")
    fun getInfAboutUser(@PathVariable userId: Int, @PathVariable companyId: Int):UserDto =
        communityService.getInfAboutUser(userId,companyId)



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


    @Operation(method = "Добавление участника в группу по идентификатору группы")
    @PostMapping("/group/{id}")
    fun groupCreateUser(@PathVariable id: Int, @RequestBody dto: UserDto): Int? {
        return communityService.groupCreateUser(id,dto)
    }


    @Operation(method = "Проведение жеребьевки в группепо идентификатору группы")
    @PostMapping("/group/{companyId}/toss")
    fun groupCreateUser(@PathVariable companyId: Int) :MutableList<UserDto>{
        return communityService.sortUsers(companyId)
    }


    @Operation(method = "Обновить учетную запись по id")
    @PutMapping("/group/{id}")
    @Parameter(description = "id - уникальный идентификатор пользователя или компании")
    fun update(@PathVariable id: Int, @RequestBody dto: TempDto) {
        return communityService.update(id,dto)
    }

    @Operation(method = "Удалить учетную запись по id")
    @DeleteMapping("/{id}")
    @Parameter(description = "id - уникальный идентификатор пользователя или компании")
    fun delete(@PathVariable id: Int){
        return communityService.delete(id)

    }

    @Operation(method = "Удаление участника из группы по идентификаторам группы участника")
    @DeleteMapping("group/{companyId}/participant/{userId}")
    @Parameter(description = "id - уникальный идентификатор пользователя или компании")
    fun deleteById(@PathVariable userId: Int, @PathVariable companyId: Int){
        return communityService.deleteById(userId, companyId)

    }

    /*
    Скрыть аннотацию к методу можно при помощи
    @Parameter(hidden = true)
     */

}