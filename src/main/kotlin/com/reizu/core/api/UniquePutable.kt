package com.reizu.core.api

import com.reizu.core.entity.UniqueEntityable
import java.util.UUID

/**
 * Methods which allow PUT on endpoint for unique id entities
 *
 * @property T Type of entity
 */
interface UniquePutable<T : UniqueEntityable> {
    /**
     * Replaces entity with [id]
     *
     * @param id ID of entity to replace
     * @param entity Entity
     * @return Replaced entity
     */
    fun replace(id: UUID, entity: T) : T
}
