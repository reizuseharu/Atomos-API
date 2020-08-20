package com.reizu.core.api

import com.reizu.core.entity.UniqueEntityable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

/**
 * Methods which allow GET on endpoint for unique id entities
 *
 * @property T Type of entity
 */
interface UniqueGetable<T : UniqueEntityable> {
    /**
     * Gets Entity with [id]
     *
     * @param id ID of entity to get
     * @return Retrieved entity
     */
    fun find(id: UUID): T

    /**
     * Get all entities by input id
     *
     * @return All [T] entities
     */
    fun findAllById(ids: Iterable<UUID>): List<T>

    /**
     * Get all filtered entities
     *
     * @param page [Pageable] instance that allows pagination and sorting
     * @param search Used for specifying entities to retrieve
     * @return [Page] of filtered entities
     */
    fun findAll(page: Pageable? = null, search: String? = null): Iterable<T>

    /**
     * Get all filtered entities that have not been removed
     *
     * @param page [Pageable] instance that allows pagination and sorting
     * @param search Used for specifying entities to retrieve
     * @return [Page] of filtered entities
     */
    fun findAllActive(page: Pageable? = null, search: String? = null): Iterable<T>
}
