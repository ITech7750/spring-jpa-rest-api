package ru.itech.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import ru.itech.dto.CommunityDto
import ru.itech.model.NameOnly
import ru.itech.entity.CommunityEntity

interface CommunityRepository: CrudRepository<CommunityEntity,Int>{
    fun findByOrderById(pageable: Pageable): List<CommunityEntity>
    // findByTitleStartsWithIgnoreCaseOrderByTitle  - не сохранять регистр
    fun findByNameStartsWithOrderByName(prefix: String): List<CommunityEntity>

    fun findAllByOrderByName(): List<NameOnly>


}