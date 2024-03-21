package ru.itech.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import ru.itech.controller.model.TitleOnly
import ru.itech.entity.CommunityEntity

interface CommunityRepository: CrudRepository<CommunityEntity,Int>{
    fun findByOrderById(pageable: Pageable): List<CommunityEntity>
    // findByTitleStartsWithIgnoreCaseOrderByTitle  - не сохранять регистр
    fun findByTitleStartsWithOrderByTitle(prefix: String): List<CommunityEntity>

    fun findAllByOrderByTitle(): List<TitleOnly>
}