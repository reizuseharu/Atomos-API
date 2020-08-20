package com.reizu.core.api.test.unit.exception

import com.reizu.core.entity.Entityable
import com.reizu.core.test.Testable
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Unit tests for ExceptionHandler
 */
abstract class BaseResponseEntityExceptionHandlerTest<T : Entityable> : Testable {

    // - Look into how to test ExceptionHandler

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

    @Test
    @DisplayName("given invalid request on entity - when EntityNotFoundException thrown - returns NotFound response")
    fun testThrownEntityNotFoundExceptionReturnsNotFound() {}

    @Test
    @DisplayName("given invalid request on entity - when RollbackException thrown - returns BadRequest response")
    fun testThrownRollbackExceptionReturnsBadRequest() {}

    @Test
    @DisplayName("given invalid request on entity - when {INSERT EXCEPTION HERE} thrown - returns Forbidden response")
    fun testThrownXXXExceptionReturnsForbidden() {}

    @Test
    @DisplayName("given invalid request on entity - when IllegalArgumentException thrown - returns Conflict response")
    fun testThrownIllegalArgumentExceptionReturnsConflict() {}

    @Test
    @DisplayName("given invalid request on entity - when IllegalStateException thrown - returns Conflict response")
    fun testThrownIllegalStateExceptionReturnsConflict() {}

}
