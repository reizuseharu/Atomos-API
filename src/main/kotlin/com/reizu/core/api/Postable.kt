package com.reizu.core.api

import com.reizu.core.entity.Entityable

/**
 * Methods which allow POST on endpoint
 *
 * @property T Type of entity
 */
interface Postable<T : Entityable> {
    /**
     * Creates [entity]
     *
     * @param entity Entity
     * @return Created entity
     */
    fun create(entity: T): T
}
