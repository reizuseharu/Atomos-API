package com.reizu.core.api.test.unit.filter

import com.reizu.core.api.filter.SearchCriterion
import com.reizu.core.api.filter.SearchSpecification
import com.reizu.core.api.filter.SearchSpecificationsBuilder
import com.reizu.core.entity.Entityable
import com.reizu.core.test.Testable
import com.nhaarman.mockitokotlin2.whenever
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Unit tests for SearchSpecification
 */
abstract class SearchSpecificationsBuilderTest<T : Entityable> : Testable {

    protected abstract val searchSpecificationsBuilder: SearchSpecificationsBuilder<T>

    protected abstract val searchSpecification: SearchSpecification<T>
    protected abstract val searchSpecifications: List<SearchSpecification<T>>
    protected abstract val groupedSpecification: SearchSpecification<T>

    protected abstract val params: MutableList<SearchCriterion>

    protected abstract val validSearchCriterion: SearchCriterion
    protected abstract val invalidSearchCriterion: SearchCriterion

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

    // ! Modify class or tests to actually use mocks

    @Nested
    inner class Success {
        @Test
        @DisplayName("given valid search parameters - when Specification built - returns Specification")
        fun testBuildSpecificationWithValidSearchQueryParameters() {
            whenever(params.isEmpty()).thenReturn(false)
            whenever(SearchSpecification<T>(validSearchCriterion)).thenReturn(searchSpecification)
            whenever(searchSpecification.and(searchSpecification)).thenReturn(groupedSpecification)

            searchSpecificationsBuilder.build() shouldEqual searchSpecification
        }

        @Test
        @DisplayName("given no search parameters - when Specification built - returns null")
        fun testBuildSpecificationWithNoSearchQueryParameters() {
            whenever(searchSpecifications.reduce<Any, Any>{ _, _ ->}).thenReturn(null)

            searchSpecificationsBuilder.build() shouldEqual null
        }
    }

    @Nested
    inner class Failure {
        @Test
        @DisplayName("given invalid search parameters - when Specification built - throws RuntimeException")
        fun testBuildSpecificationWithInvalidSearchQueryParameters() {
            whenever(params.map<SearchCriterion, Any>{}).thenThrow(RuntimeException::class.java)

            assertThrows<RuntimeException> {
                searchSpecificationsBuilder.build()
            }
        }
    }

}
