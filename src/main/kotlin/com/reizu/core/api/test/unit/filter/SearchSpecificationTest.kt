package com.reizu.core.api.test.unit.filter

import com.reizu.core.api.filter.SearchCriterion
import com.reizu.core.api.filter.SearchSpecification
import com.reizu.core.entity.Entityable
import com.reizu.core.test.Testable
import com.nhaarman.mockitokotlin2.whenever
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Path
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

/**
 * Unit tests for SearchSpecification
 */
abstract class SearchSpecificationTest<T : Entityable> : Testable {

    protected abstract val root: Root<T>
    protected abstract val query: CriteriaQuery<*>
    protected abstract val builder: CriteriaBuilder

    protected abstract val key: Path<String>

    protected abstract val validPredicate: Predicate

    protected abstract val validGreaterThanSearchCriterion: SearchCriterion
    protected abstract val validLessThanSearchCriterion: SearchCriterion
    protected abstract val validEqualitySearchCriterion: SearchCriterion
    protected abstract val validLikeSearchCriterion: SearchCriterion
    protected abstract val validInequalitySearchCriterion: SearchCriterion
    protected abstract val validNotLikeSearchCriterion: SearchCriterion
    protected abstract val validInSearchCriterion: SearchCriterion

    protected abstract val invalidKeySearchCriterion: SearchCriterion
    protected abstract val invalidOperationSearchCriterion: SearchCriterion

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
        @DisplayName("given valid greater than search criterion - when values processed - returns Predicate")
        fun testProcessValidGreaterThanSearchCriterion() {
            whenever(root.get<String>(validGreaterThanSearchCriterion.key)).thenReturn(key)
            whenever(builder.greaterThanOrEqualTo(key, validGreaterThanSearchCriterion.value.toString())).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(validGreaterThanSearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate shouldEqual validPredicate
        }

        @Test
        @DisplayName("given valid less than search criterion - when values processed - returns Predicate")
        fun testProcessValidLessThanSearchCriterion() {
            whenever(root.get<String>(validLessThanSearchCriterion.key)).thenReturn(key)
            whenever(builder.lessThanOrEqualTo(key, validLessThanSearchCriterion.value.toString())).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(validLessThanSearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate shouldEqual validPredicate
        }

        @Test
        @DisplayName("given valid equality search criterion - when values processed - returns Predicate")
        fun testProcessValidEqualitySearchCriterion() {
            whenever(root.get<String>(validEqualitySearchCriterion.key)).thenReturn(key)
            whenever(builder.equal(key, validEqualitySearchCriterion.value)).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(validEqualitySearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate shouldEqual validPredicate
        }

        @Test
        @DisplayName("given valid like search criterion - when values processed - returns Predicate")
        fun testProcessValidLikeSearchCriterion() {
            whenever(root.get<String>(validLikeSearchCriterion.key)).thenReturn(key)
            whenever(builder.like(key, validLikeSearchCriterion.value.toString().replace("*", "%"))).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(validLikeSearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate shouldEqual validPredicate
        }

        @Test
        @DisplayName("given valid inequality search criterion - when values processed - returns Predicate")
        fun testProcessValidInequalitySearchCriterion() {
            whenever(root.get<String>(validInequalitySearchCriterion.key)).thenReturn(key)
            whenever(builder.notEqual(key, validInequalitySearchCriterion.value)).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(validInequalitySearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate shouldEqual validPredicate
        }

        @Test
        @DisplayName("given valid not like search criterion - when values processed - returns Predicate")
        fun testProcessValidNotLikeSearchCriterion() {
            whenever(root.get<String>(validNotLikeSearchCriterion.key)).thenReturn(key)
            whenever(builder.notLike(key, validNotLikeSearchCriterion.value.toString().replace("*", "%"))).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(validNotLikeSearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate shouldEqual validPredicate
        }

        @Test
        @DisplayName("given valid in search criterion - when values processed - returns Predicate")
        fun testProcessValidInSearchCriterion() {
            whenever(root.get<String>(validInSearchCriterion.key)).thenReturn(key)
            whenever(key.`in`(validInSearchCriterion.value.toString().split(","))).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(validInSearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate shouldEqual validPredicate
        }
    }

    @Nested
    inner class Failure {
        @Test
        @DisplayName("given invalid search key - when values processed - throws IllegalArgumentException")
        fun testProcessInvalidKeySearchCriterion() {
            whenever(root.get<String>(invalidKeySearchCriterion.key)).thenThrow(IllegalArgumentException::class.java)

            Assertions.assertThrows(IllegalArgumentException::class.java) {
                val searchSpecification: SearchSpecification<T> = SearchSpecification(invalidKeySearchCriterion)
                searchSpecification.toPredicate(root, query, builder)
            }
        }

        @Test
        @DisplayName("given invalid search operation - when values processed - returns null")
        fun testProcessInvalidOperationSearchCriterion() {
            whenever(root.get<String>(invalidOperationSearchCriterion.key)).thenReturn(key)
            whenever(builder.equal(key, invalidOperationSearchCriterion.value)).thenReturn(validPredicate)

            val searchSpecification: SearchSpecification<T> = SearchSpecification(invalidOperationSearchCriterion)

            val predicate: Predicate? = searchSpecification.toPredicate(root, query, builder)

            predicate.shouldBeNull()
        }
    }

}
