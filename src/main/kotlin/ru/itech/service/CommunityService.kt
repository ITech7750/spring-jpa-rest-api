package ru.itech.service


import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import ru.itech.dto.CommunityDto
import ru.itech.dto.TempDto
import ru.itech.dto.UserDto


interface CommunityService {
    fun getAll(pageIndex: Int) : List<CommunityDto>

    fun getAllWithOutParticipants() : List<TempDto>

    fun getById(id: Int):CommunityDto

    //fun getDepById(id: Int): DependenceDto

    fun search(prefix: String): List<CommunityDto>

    fun getCommunityTitle():List<String>

    fun create(dto: CommunityDto): Int

    fun groupcreate(dto: TempDto): Int

    fun groupCreateUser(id: Int, dto: UserDto?): Int?

    fun update(id: Int,dto: TempDto)

    fun delete(id: Int)

    fun deleteById(userId: Int,companyId: Int)
}


