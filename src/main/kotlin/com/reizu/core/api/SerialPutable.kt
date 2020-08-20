package com.reizu.core.api

import com.reizu.core.entity.SerialEntityable

/**
 * Methods which allow PUT on endpoint for serial entities
 *
 * @property T Type of entity
 */
interface SerialPutable<T : SerialEntityable> {
    /**
     * Replaces entity with [id]
     *
     * @param id ID of entity to replace
     * @param entity Entity
     * @return Replaced entity
     */
    fun replace(id: Long, entity: T) : T
}
