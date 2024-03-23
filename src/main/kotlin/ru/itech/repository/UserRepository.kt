package ru.itech.repository

import org.springframework.data.repository.CrudRepository
import ru.itech.dto.CommunityDto
import ru.itech.dto.UserDto
import ru.itech.entity.CommunityEntity
import ru.itech.entity.UserEntity

interface UserRepository: CrudRepository<UserEntity,Int> {
    fun deleteAllByCommunityId(communityId: CommunityEntity)

    fun findByName(name:String): UserDto


}