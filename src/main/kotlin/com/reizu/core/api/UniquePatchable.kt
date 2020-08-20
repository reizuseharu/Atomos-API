package com.reizu.core.api

import com.reizu.core.entity.UniqueEntityable
import java.util.UUID

/**
 * Methods which allow PATCH on endpoint for unique id entities
 *
 * @property T Type of entity
 */
interface UniquePatchable<T : UniqueEntityable> {
    /**
     * Modifies fields on entity with [id]
     *
     * @param id ID of entity to modify
     * @param patchedFields Map of fields to change
     * @return Modified entity
     */
    fun modify(id: UUID, patchedFields: Map<String, Any>): T
}
