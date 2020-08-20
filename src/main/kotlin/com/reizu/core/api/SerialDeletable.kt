package com.reizu.core.api

import com.reizu.core.entity.SerialEntityable

/**
 * Methods which allow DELETE on endpoint for serial entities
 *
 * @property T Type of entity
 */
interface SerialDeletable<T : SerialEntityable> {
    /**
     * Removes Entity with [id] by setting removedOn field to a datetime
     *
     * @param id ID of entity to remove
     */
    fun remove(id: Long)

    /**
     * Removes all entities by setting removedOn field to a datetime
     *
     * @param search Used for specifying entities to remove
     */
    fun removeAll(search: String?)
}
