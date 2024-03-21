package ru.itech.service


import ru.itech.dto.CommunityDto


interface CommunityService {
    fun getAll(pageIndex: Int) : List<CommunityDto>

    fun getById(id: Int):CommunityDto

    fun search(prefix: String): List<CommunityDto>

    fun getCommunityTitle():List<String>

    fun create(dto: CommunityDto): Int

    fun update(id: Int,dto: CommunityDto)

    fun delete(id: Int)
}


