package com.reizu.core.api.service

import com.reizu.core.api.filter.SearchSpecificationsBuilder
import com.reizu.core.api.utility.Utility.SEARCH_PATTERN
import com.reizu.core.entity.Entityable
import org.springframework.data.jpa.domain.Specification
import java.util.regex.Pattern

/**
 * Service which parses search query parameter
 */
abstract class SearchService<T : Entityable> {

    /**
     * Parses search query parameter to generate Specification
     *
     * @param search Query parameter to parse
     * @return Specification
     */
    fun generateSpecification(search: String?): Specification<T>? {

        val builder = SearchSpecificationsBuilder<T>()

        val pattern = Pattern.compile(SEARCH_PATTERN)
        val matcher = pattern.matcher("$search;")

        while (matcher.find()) {
            val key: String = matcher.group(1)
            val operation: String = matcher.group(2)
            val value: Any = matcher.group(3)

            builder.with(key, operation, value)
        }

        return builder.build()
    }

}
