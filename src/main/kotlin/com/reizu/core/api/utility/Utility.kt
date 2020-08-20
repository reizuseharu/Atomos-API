package com.reizu.core.api.utility

/**
 * Configuration
 *
 * Stores configuration values and methods needed to acquire them
 */
object Utility {
    const val DEFAULT_PAGE_SIZE: Int = 100
    const val DEFAULT_SORT_FIELD: String = "id"

    const val SEARCH_PATTERN: String = "([\\w\\.]+?)([~:|<>^!])([\\w\\s\\*]*?);"
}
