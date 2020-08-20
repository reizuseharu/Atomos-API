package com.reizu.core.api.exception

import com.reizu.core.api.test.example.Solid
import com.reizu.core.api.test.unit.exception.BaseResponseEntityExceptionHandlerTest
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

/**
 * Unit tests for Solid ExceptionHandler
 */
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidResponseEntityExceptionHandlerTest : BaseResponseEntityExceptionHandlerTest<Solid>()
