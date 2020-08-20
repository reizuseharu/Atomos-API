package com.reizu.core.api.controller

import com.reizu.core.api.test.Application
import com.reizu.core.api.test.example.Solid
import com.reizu.core.api.test.example.SolidService
import com.reizu.core.api.test.unit.controller.BaseSerialControllerTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.stereotype.Controller
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

/**
 * Unit tests for SolidController
 */
@Disabled
@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [Application::class])
@WebMvcTest(Controller::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidControllerTest : BaseSerialControllerTest<Solid>() {

    @Autowired
    override lateinit var mvc: MockMvc

    @MockBean
    override lateinit var service: SolidService

    override val validId: Long = 1
    override val invalidId: Long = -1

    @Mock
    override lateinit var validEntity: Solid
//
//    @Mock
//    override lateinit var invalidEntity: Solid
//
//    @Mock
//    override lateinit var replacedEntity: Solid

    override val validEntityBody: String = "{\"id\": 1}"
    override val invalidEntityBody: String = "{\"invalidId\": 1}"

//    @Mock
//    override lateinit var validPatchedFields: Map<String, Any>
//
//    @Mock
//    override lateinit var invalidPatchedFields: Map<String, Any>

    override val validPatchedFieldsBody: String = "{\"id\": 2}"
    override val invalidPatchedFieldsBody: String = "{\"invalidId\": 2}"

//    @Mock
//    override lateinit var validPageable: Pageable
//
//    @Mock
//    override lateinit var invalidPageable: Pageable

    @Mock
    override lateinit var validPage: Page<Solid>

    @Mock
    override lateinit var validEntities: List<Solid>

    override val validCount: Long = 2L

    override val validSearchParam: String = "id:2"
    override val invalidSearchParam: String = "id`2"

    override val validPageParam: String = "1"
    override val invalidPageParam: String = "a"

    override val entityName: String = "solid"

}
