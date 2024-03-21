package ru.itech.service.implementation

import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.itech.dto.CommunityDto
import ru.itech.dto.UserDto
import ru.itech.entity.CommunityEntity
import ru.itech.entity.UserEntity
import ru.itech.exception.CommunityNotFoundException
import ru.itech.repository.CommunityRepository
import ru.itech.repository.UserRepository
import ru.itech.service.CommunityService
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Private

@Service
class CommunityServiceImlementation (
        private val communityRepository: CommunityRepository,
        private val userRepository: UserRepository,
): CommunityService{

    override fun getAll(pageIndex: Int): List<CommunityDto> {
        return communityRepository.findByOrderById(PageRequest.of(pageIndex,2))
            .map { it.toDto() }
    }

    override fun getById(id: Int): CommunityDto {
        return communityRepository.findByIdOrNull(id)
            ?.toDto()
            ?: throw CommunityNotFoundException(id)
    }

    override fun search(prefix: String): List<CommunityDto> {
        return communityRepository.findByTitleStartsWithOrderByTitle(prefix)
            .map { it.toDto() }
    }

    override fun getCommunityTitle(): List<String> =
        communityRepository.findAllByOrderByTitle().map { it.title }


    @Transactional
    override fun create(dto: CommunityDto): Int {
        val communityEntity = communityRepository.save(dto.toEntity())
        val members = dto.members.map{it.toEntity(communityEntity)}
        userRepository.saveAll(members)
        return communityEntity.id

        //return communityRepository.save(dto.toEntity()).toDto()
    }

    /*
    override fun update(id: Int, dto: CommunityDto): CommunityDto {
        val upd = communityRepository.findByIdOrNull(id)
            ?: throw RuntimeException("Not found")
        upd.title = dto.title
        upd.description = dto.description
        communityRepository.save(upd)
        return (communityRepository.findByIdOrNull(id)
            ?.toDto()
            ?: CommunityNotFoundException(id)) as CommunityDto
    }
     */
    @Transactional
    override fun update(id: Int, dto: CommunityDto){
        var upd = communityRepository.findByIdOrNull(id)
            ?: throw RuntimeException("Not found")
        upd.title = dto.title
        upd.description = dto.description
        upd = communityRepository.save(upd)
        val members = dto.members.map{it.toEntity(upd)}
        userRepository.deleteAllByCommunityId(upd)
        userRepository.saveAll(members)
    }
    /*
    override fun delete(id: Int) {
        val upd = communityRepository.findByIdOrNull(id)
            ?: throw CommunityNotFoundException(id)
        communityRepository.delete(upd)
    }
    */
    @Transactional
    override fun delete(id: Int) {
        val upd = communityRepository.findByIdOrNull(id)
            ?: throw CommunityNotFoundException(id)
        userRepository.deleteAllByCommunityId(upd)
        communityRepository.delete(upd)
    }

    // метод расширения toDto чтобы "мапить" сущность с БД в dto
    private fun CommunityEntity.toDto(): CommunityDto = CommunityDto(
            id = this.id,
            title = this.title,
            description = this.description,
            members = this.members.map {it.toDto() }
    )

    // метод расширения toDto чтобы "мапить" сущность с БД в dto
    private fun CommunityDto.toEntity(): CommunityEntity = CommunityEntity(
        id = 0,
        title = this.title,
        description = this.description
    )
    private fun UserEntity.toDto(): UserDto =
        UserDto(
            title = this.title,
            id = this.id
        )
    private fun UserDto.toEntity(community: CommunityEntity): UserEntity =
        UserEntity(
            id = 0,
            title = this.title,
            communityId = community
        )
}