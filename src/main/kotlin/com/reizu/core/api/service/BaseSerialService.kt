package com.reizu.core.api.service

import com.fasterxml.jackson.module.kotlin.convertValue
import com.reizu.core.api.FullySerialControllable
import com.reizu.core.api.repository.BaseSerialRepository
import com.reizu.core.entity.SerialEntityable
import com.reizu.core.util.Resource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime

/**
 * Abstract base service
 */
abstract class BaseSerialService<T : SerialEntityable>(private val classType: Class<T>) : FullySerialControllable<T> {

    @Autowired
    private lateinit var repository: BaseSerialRepository<T>

    @Autowired
    private lateinit var searchService: SearchService<T>

    override fun find(id: Long): T {
        return repository.getOne(id)
    }

    override fun findAllById(ids: Iterable<Long>): List<T> {
        return repository.findAllById(ids)
    }

    override fun findAll(page: Pageable?, search: String?): Iterable<T> {
        return search?.let { _search ->
            val searchSpecification: Specification<T>? = searchService.generateSpecification(_search)
            page?.let { _page ->
                repository.findAll(searchSpecification, _page)
            } ?: run {
                repository.findAll(searchSpecification)
            }
        } ?: run {
            repository.findAll()
        }
    }

    override fun findAllActive(page: Pageable?, search: String?): Iterable<T> {
        val allEntities: Iterable<T> = findAll(page, search)

        return allEntities.filter { entity -> entity.removedOn == null }
    }

    override fun count(search: String?): Long {
        return search?.let {
            val searchSpecification: Specification<T>? = searchService.generateSpecification(it)
            repository.count(searchSpecification)
        } ?: run {
            repository.count()
        }
    }

    override fun create(entity: T): T {
        return repository.save(entity)
    }

    @Deprecated("PUT doesn't work generically")
    override fun replace(id: Long, entity: T): T {
        val originalEntity: T = repository.getOne(id)
        entity._id = originalEntity.id
        return repository.save(entity)
    }

    @Deprecated("PUT doesn't work generically")
    override fun modify(id: Long, patchedFields: Map<String, Any>): T {
        val originalEntity: T = repository.getOne(id)

        val objectMapper = Resource.OBJECT_MAPPER
        val originalMap: Map<String, Any> = objectMapper.convertValue(originalEntity)
        val patchedMap: Map<String, Any> = originalMap.plus(patchedFields)

        val patchedEntity: T = objectMapper.convertValue(patchedMap, classType)

        return repository.save(patchedEntity)
    }

    private fun removeEntity(removableEntity: T?) {
        removableEntity?.let { _removableEntity ->
            _removableEntity.removedOn = LocalDateTime.now()
            repository.save(_removableEntity)
        }
    }

    override fun remove(id: Long) {
        val removableEntity: T = repository.getOne(id)
        removeEntity(removableEntity)
    }

    override fun removeAll(search: String?) {
        val removableEntities: Iterable<T> = findAllActive(search = search)
        removableEntities.forEach { removableEntity ->
            removeEntity(removableEntity)
        }
    }

}
