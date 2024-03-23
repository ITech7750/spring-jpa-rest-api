package ru.itech.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.RequestBody
import ru.itech.dto.CommunityDto
import ru.itech.dto.UserDto
import ru.itech.entity.CommunityEntity
import ru.itech.entity.UserEntity
import ru.itech.model.NameOnly

interface UserRepository: CrudRepository<UserEntity,Int> {
    fun deleteAllByCommunityId(communityId: CommunityEntity)

    fun findByName(name:String): UserDto

    fun findAllByCommunityId(communityId: CommunityEntity): List<UserDto>
}