package com.reizu.core.api

import com.reizu.core.entity.SerialEntityable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Methods which allow GET on endpoint for serial entities
 *
 * @property T Type of entity
 */
interface SerialGetable<T : SerialEntityable> {
    /**
     * Gets Entity with [id]
     *
     * @param id ID of entity to get
     * @return Retrieved entity
     */
    fun find(id: Long): T

    /**
     * Get all entities by input id
     *
     * @return All [T] entities
     */
    fun findAllById(ids: Iterable<Long>): List<T>

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
