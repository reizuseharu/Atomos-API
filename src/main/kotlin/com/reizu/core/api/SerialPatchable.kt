package com.reizu.core.api

import com.reizu.core.entity.SerialEntityable

/**
 * Methods which allow PATCH on endpoint
 *
 * @property T Type of entity for serial entities
 */
interface SerialPatchable<T : SerialEntityable> {
    /**
     * Modifies fields on entity with [id]
     *
     * @param id ID of entity to modify
     * @param patchedFields Map of fields to change
     * @return Modified entity
     */
    fun modify(id: Long, patchedFields: Map<String, Any>): T
}
