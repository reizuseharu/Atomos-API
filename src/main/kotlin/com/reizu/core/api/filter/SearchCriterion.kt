package com.reizu.core.api.filter

/**
 * Criteria based on search query parameters
 *
 * @property key Field to filter on
 * @property operation Filtering action
 * @property value Value to filter
 */
data class SearchCriterion(
    val key: String,
    val operation: String,
    val value: Any
)
