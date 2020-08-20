package com.reizu.core.api.service

import com.reizu.core.api.test.example.Solid
import com.reizu.core.api.test.example.SolidSearchService
import com.reizu.core.api.test.unit.service.SearchServiceTest
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.springframework.test.context.junit.jupiter.SpringExtension

/**
 * Unit tests for SearchService
 */
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidSearchServiceTest : SearchServiceTest<Solid>() {

    @InjectMocks
    override lateinit var searchService: SolidSearchService

    override val validSearch: String? = "id:2"
    override val invalidSearch: String? = "id`2"
    override val nullSearch: String? = null

}
