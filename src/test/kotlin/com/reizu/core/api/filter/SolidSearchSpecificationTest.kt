package com.reizu.core.api.filter

import com.reizu.core.api.test.example.Solid
import com.reizu.core.api.test.unit.filter.SearchSpecificationTest
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Path
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

/**
 * Unit tests for SolidSearchSpecification
 */
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidSearchSpecificationTest: SearchSpecificationTest<Solid>() {

    @Mock
    override lateinit var root: Root<Solid>

    @Mock
    override lateinit var query: CriteriaQuery<*>

    @Mock
    override lateinit var builder: CriteriaBuilder

    @Mock
    override lateinit var key: Path<String>

    @Mock
    override lateinit var validPredicate: Predicate

    override val validGreaterThanSearchCriterion = SearchCriterion("id", ">", 2)
    override val validLessThanSearchCriterion = SearchCriterion("id", "<", 2)
    override val validEqualitySearchCriterion = SearchCriterion("id", ":", 2)
    override val validLikeSearchCriterion = SearchCriterion("id", "~", "2*")
    override val validInequalitySearchCriterion = SearchCriterion("id", "!", 2)
    override val validNotLikeSearchCriterion = SearchCriterion("id", "^", "2*")
    override val validInSearchCriterion = SearchCriterion("id", "|", "2,3")

    override val invalidKeySearchCriterion = SearchCriterion("ig", ":", 2)
    override val invalidOperationSearchCriterion = SearchCriterion("id", "`", 2)

}
