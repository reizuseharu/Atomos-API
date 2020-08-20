package com.reizu.core.api.test.unit.service

import com.reizu.core.api.service.SearchService
import com.reizu.core.entity.Entityable
import com.reizu.core.test.Testable
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/**
 * Unit tests for SearchService
 */
abstract class SearchServiceTest<T : Entityable> : Testable {

    protected abstract val searchService: SearchService<T>

    protected abstract val validSearch: String?
    protected abstract val invalidSearch: String?
    protected abstract val nullSearch: String?

    @BeforeAll
    override fun preSetUp() {
        // Subclass implementation
    }

    @BeforeEach
    override fun setUp() {
        // Subclass implementation
    }

    @AfterEach
    override fun tearDown() {}

    @AfterAll
    override fun postTearDown() {}

    @Nested
    inner class Success {
        @Test
        @DisplayName("given valid search - when Specification generated - returns Specification")
        fun testGenerateValidSpecificationWithValidSearchQueryParameter() {
            val validSpecification = searchService.generateSpecification(validSearch)

            validSpecification.shouldNotBeNull()
        }

        @Test
        @DisplayName("given invalid search - when Specification generated - returns null")
        fun testGenerateNullSpecificationWithInvalidSearchQueryParameter() {
            val invalidSpecification = searchService.generateSpecification(invalidSearch)

            invalidSpecification.shouldBeNull()
        }

        @Test
        @DisplayName("given null search - when Specification generated - returns null")
        fun testGenerateNullSpecificationWithNullSearchQueryParameter() {
            val nullSpecification = searchService.generateSpecification(nullSearch)

            nullSpecification.shouldBeNull()
        }
    }

    @Nested
    inner class Failure
}
