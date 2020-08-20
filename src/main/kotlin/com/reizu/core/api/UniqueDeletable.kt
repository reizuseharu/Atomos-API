package com.reizu.core.api

import com.reizu.core.entity.UniqueEntityable
import java.util.UUID

/**
 * Methods which allow DELETE on endpoint for unique id entities
 *
 * @property T Type of entity
 */
interface UniqueDeletable<T : UniqueEntityable> {
    /**
     * Removes Entity with [id] by setting removedOn field to a datetime
     *
     * @param id ID of entity to remove
     */
    fun remove(id: UUID)

    /**
     * Removes all entities by setting removedOn field to a datetime
     *
     * @param search Used for specifying entities to remove
     */
    fun removeAll(search: String?)
}
