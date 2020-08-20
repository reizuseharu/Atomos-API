package com.reizu.core.api

/**
 * Methods which allow Count
 */
interface Countable {
    /**
     * Count all entities after filtering
     *
     * @param search Used for specifying entities to retrieve
     * @return Count of filtered entities
     */
    fun count(search: String? = null): Long
}
