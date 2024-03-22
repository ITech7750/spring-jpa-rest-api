package ru.itech.service.implementation

import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.itech.dto.CommunityDto
import ru.itech.dto.TempDto
import ru.itech.dto.UserDto
import ru.itech.entity.CommunityEntity
import ru.itech.entity.UserEntity
import ru.itech.exception.CommunityNotFoundException
import ru.itech.repository.CommunityRepository
import ru.itech.repository.UserRepository
import ru.itech.service.CommunityService

@Service
class CommunityServiceImplementation (
        private val communityRepository: CommunityRepository,
        private val userRepository: UserRepository,
): CommunityService{

    override fun getAll(pageIndex: Int): List<CommunityDto> {
        return communityRepository.findByOrderById(PageRequest.of(pageIndex,2))
            .map { it.toDto() }
    }

    override fun getAllWithOutParticipants(): List<TempDto> {
        return communityRepository.findAll()
            .map{ it.toTempDto() }
    }


    override fun getById(id: Int): CommunityDto {
        return communityRepository.findByIdOrNull(id)
            ?.toDto()
            ?: throw CommunityNotFoundException(id)
    }

    override fun search(prefix: String): List<CommunityDto> {
        return communityRepository.findByNameStartsWithOrderByName(prefix)
            .map { it.toDto() }
    }

    override fun getCommunityTitle(): List<String> =
        communityRepository.findAllByOrderByName().map { it.name}


    @Transactional
    override fun create(dto: CommunityDto): Int {
        val communityEntity = communityRepository.save(dto.toEntity())
        val members = dto.participants.map{it.toEntity(communityEntity)}
        userRepository.saveAll(members)
        return communityEntity.id

        //return communityRepository.save(dto.toEntity()).toDto()
    }


    override fun groupcreate(dto: TempDto): Int {
        val communityEntity = dto.let { communityRepository.save(it.toEntity()) }
            return communityEntity.id
    }

    @Transactional
    override fun groupCreateUser(id: Int, dto: UserDto?): Int? {
        val community = communityRepository.findByIdOrNull(id)
        if (dto != null) {
            community?.let { dto.toEntity(it) }?.let {userRepository.save(it) }
        }
        val tmp = dto?.id
        return tmp
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
    override fun update(id: Int, dto: TempDto){
        var upd = communityRepository.findByIdOrNull(id)
            ?: throw RuntimeException("Not found")
        upd.name = dto.name
        upd.description = dto.description
        upd = communityRepository.save(upd)
        //val members = dto.participants.map{it.toEntity(upd)}
        //userRepository.deleteAllByCommunityId(upd)
        //userRepository.saveAll(members)
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
            name = this.name,
            description = this.description,
            participants = this.members.map {it.toDto() }
    )

    private fun CommunityEntity.toTempDto(): TempDto = TempDto(
        id = this.id,
        name = this.name,
        description = this.description
    )

    // метод расширения toDto чтобы "мапить" сущность с БД в dto
    private fun CommunityDto.toEntity(): CommunityEntity = CommunityEntity(
        id = 0,
        name = this.name,
        description = this.description
    )
    private fun TempDto.toEntity(): CommunityEntity = CommunityEntity(
        id = 0,
        name = this.name,
        description = this.description
    )
    private fun UserEntity.toDto(): UserDto =
        UserDto(
            name = this.name,
            id = this.id,
            wish = this.wish,
            dependId = this.dependId
        )
    private fun UserDto.toEntity(community: CommunityEntity): UserEntity =
        UserEntity(
            id = 0,
            name = this.name,
            communityId = community,
            dependId = this.dependId,
            wish = this.wish
        )

}