package com.reizu.core.api.test.unit.controller

import com.reizu.core.api.service.BaseSerialService
import com.reizu.core.entity.SerialEntityable
import com.reizu.core.test.Testable
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.whenever
import org.amshove.kluent.shouldBeBlank
import org.amshove.kluent.shouldNotBeBlank
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Page
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import javax.persistence.EntityNotFoundException
import javax.persistence.RollbackException

/**
 * Unit tests for Controller
 */
abstract class BaseSerialControllerTest<T : SerialEntityable> : Testable {

    protected abstract val mvc: MockMvc

    protected abstract val service: BaseSerialService<T>

    protected abstract val entityName: String

    protected abstract val validId: Long
    protected abstract val invalidId: Long

    protected abstract val validEntity: T

    protected abstract val validEntityBody: String
    protected abstract val invalidEntityBody: String

    protected abstract val validPatchedFieldsBody: String
    protected abstract val invalidPatchedFieldsBody: String

    protected abstract val validPage: Page<T>

    protected abstract val validEntities: List<T>

    protected abstract val validSearchParam: String
    protected abstract val invalidSearchParam: String

    protected abstract val validCount: Long

    protected abstract val validPageParam: String
    protected abstract val invalidPageParam: String

    private lateinit var baseUri: String
    private lateinit var idUri: String
    private lateinit var countUri: String

    // - Instantiate param blocks for get and find all

    @BeforeAll
    override fun preSetUp() {
        baseUri = "/$entityName"
        idUri = "$baseUri/{id}"
        countUri = "$baseUri/count"
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
        @DisplayName("given valid id - when GET entity - returns entity")
        fun testGetEntityWithValidId() {
            whenever(service.find(validId)).thenReturn(validEntity)

            val mvcResult: MvcResult = mvc.perform(
                get(idUri, validId)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
            )
                .andExpect(status().isOk)
                .andReturn()

            mvcResult.response.contentAsString.shouldNotBeBlank()
        }

        @Test
        @DisplayName("given search parameters - when GET entities - returns entities")
        fun testGetEntitiesWithValidSearchParameters() {
            whenever(service.findAll(search = eq(validSearchParam))).thenReturn(validEntities)

            val mvcResult: MvcResult = mvc.perform(
                get(baseUri)
                    .param("search", validSearchParam)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
            )
                .andExpect(status().isOk)
                .andReturn()

            mvcResult.response.contentAsString.shouldNotBeBlank()
        }

        @Test
        @DisplayName("given valid page, sort, and search parameters - when GET entities - returns entities")
        fun testGetEntitiesWithValidQueryParameters() {
            whenever(service.findAll(any(), eq(validSearchParam))).thenReturn(validPage)

            val mvcResult: MvcResult = mvc.perform(
                get(baseUri)
                    .param("page", validPageParam)
                    .param("search", validSearchParam)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
            )
                .andExpect(status().isOk)
                .andReturn()

            mvcResult.response.contentAsString.shouldNotBeBlank()
        }

        @Test
        @DisplayName("given search parameters - when COUNT entities - returns count")
        fun testCountEntitiesWithValidSearchParameters() {
            whenever(service.count(eq(validSearchParam))).thenReturn(validCount)

            val mvcResult: MvcResult = mvc.perform(
                get(countUri)
                    .param("search", validSearchParam)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
            )
                .andExpect(status().isOk)
                .andReturn()

            mvcResult.response.contentAsString.shouldNotBeBlank()
        }

        @Test
        @DisplayName("given valid entity - when POST entity - persists entity")
        fun testPostValidEntity() {
            //whenever(service.create(any())).thenReturn(validEntity)

            val mvcResult: MvcResult = mvc.perform(
                post(baseUri)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(validEntityBody)
            )
                .andExpect(status().isOk)
                .andReturn()

            mvcResult.response.contentAsString.shouldNotBeBlank()
        }

        @Test
        @DisplayName("given valid id and entity to replace - when PUT entity - replaces entity")
        fun testPutValidEntityWithValidId() {
            //whenever(service.replace(validId, any())).thenReturn(validEntity)

            val mvcResult: MvcResult = mvc.perform(
                put(idUri, validId)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(validEntityBody)
            )
                .andExpect(status().isOk)
                .andReturn()

            mvcResult.response.contentAsString.shouldNotBeBlank()
        }

        @Test
        @DisplayName("given valid id and fields to replace - when PATCH entity - modifies entity")
        fun testPatchEntityWithValidIdAndFields() {
            whenever(service.modify(eq(validId), any())).thenReturn(validEntity)

            val mvcResult: MvcResult = mvc.perform(
                patch(idUri, validId)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(validPatchedFieldsBody)
            )
                .andExpect(status().isOk)
                .andReturn()

            mvcResult.response.contentAsString.shouldNotBeBlank()
        }

        @Test
        @DisplayName("given valid id - when DELETE entity - sets entity removedOn to now")
        fun testDeleteEntityWithValidId() {
            doNothing().whenever(service).remove(validId)

            val mvcResult: MvcResult = mvc.perform(
                delete(idUri, validId)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
            )
                .andExpect(status().isOk)
                .andReturn()

            mvcResult.response.contentAsString.shouldBeBlank()
        }

        @Test
        @DisplayName("given valid page, sort, and search parameters - when DELETE entities - sets entities' removedOn to now")
        fun testDeleteEntitiesWithValidQueryParameters() {
            doNothing().whenever(service).removeAll(eq(validSearchParam))

            val mvcResult: MvcResult = mvc.perform(
                delete(baseUri)
                    .param("search", validSearchParam)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
            )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk)
                .andReturn()

            mvcResult.response.contentAsString.shouldBeBlank()
            // ! Add rest of logic for iterating through page
        }
    }

    @Nested
    inner class Failure {
        @Nested
        inner class InvalidUriPath {
            @Test
            @DisplayName("given invalid id - when GET entity - returns NotFound response")
            fun testGetEntityWithInvalidId() {
                doThrow(EntityNotFoundException::class).whenever(service).find(invalidId)

                mvc.perform(
                    get(idUri, invalidId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                    .andExpect(status().isNotFound)
            }

            @Test
            @DisplayName("given invalid id - when PUT entity - returns NotFound response")
            fun testPutEntityWithInvalidId() {
                //doThrow(EntityNotFoundException::class).whenever(service).replace(invalidId, any())

                mvc.perform(
                    put(idUri, invalidId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(validEntityBody)
                )
                    .andExpect(status().isNotFound)
            }

            @Test
            @DisplayName("given invalid id - when PATCH entity - returns NotFound response")
            fun testPatchEntityWithInvalidId() {
                doThrow(EntityNotFoundException::class).whenever(service).modify(invalidId, any())

                mvc.perform(
                    patch(idUri, invalidId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(validPatchedFieldsBody)
                )
                    .andExpect(status().isNotFound)
            }

            @Test
            @DisplayName("given invalid id - when DELETE entity - returns NotFound response")
            fun testDeleteEntityWithInvalidId() {
                doThrow(EntityNotFoundException::class).whenever(service).remove(invalidId)

                mvc.perform(
                    delete(idUri, invalidId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                    .andExpect(status().isNotFound)
            }
        }

        @Nested
        inner class InvalidBody {
            @Test
            @DisplayName("given invalid entity - when POST entity - returns BadRequest response")
            fun testPostInvalidEntity() {
                //doThrow(RollbackException::class).whenever(service).create(any())

                mvc.perform(
                    post(baseUri)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(invalidEntityBody)
                )
                    .andExpect(status().isBadRequest)
            }

            @Test
            @DisplayName("given invalid entity - when PUT entity - returns BadRequest response")
            fun testPutInvalidEntity() {
                //doThrow(RollbackException::class).whenever(service).replace(validId, any())

                mvc.perform(
                    put(idUri, validId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(invalidEntityBody)
                )
                    .andExpect(status().isBadRequest)
            }

            @Test
            @DisplayName("given invalid partial entity - when PATCH entity - returns BadRequest response")
            fun testPatchEntityWithInvalidFields() {
                doThrow(RollbackException::class).whenever(service).modify(validId, any())

                mvc.perform(
                    put(idUri, validId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(invalidPatchedFieldsBody)
                )
                    .andExpect(status().isBadRequest)
            }
        }

        @Nested
        inner class InvalidQueryParam {
            @Test
            @DisplayName("given invalid page parameter - when GET entities - returns BadRequest response")
            fun testGetEntitiesWithInvalidPageParameter() {
                doThrow(RuntimeException::class).whenever(service).findAll(any(), validSearchParam)

                mvc.perform(
                    get(baseUri)
                        .param("page", invalidPageParam)
                        .param("search", validSearchParam)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                    .andExpect(status().isBadRequest)
            }

            @Test
            @DisplayName("given invalid sort parameter - when GET entities - returns BadRequest response")
            fun testGetEntitiesWithInvalidSortParameter() {
                doThrow(RuntimeException::class).whenever(service).findAll(any(), validSearchParam)

                mvc.perform(
                    get(baseUri)
                        .param("page", invalidPageParam)
                        .param("search", validSearchParam)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                    .andExpect(status().isBadRequest)
            }

            @Test
            @DisplayName("given invalid search parameters - when GET entities - returns BadRequest response")
            fun testGetEntitiesWithInvalidSearchParameter() {
                doThrow(RuntimeException::class).whenever(service).findAll(any(), invalidSearchParam)

                mvc.perform(
                    get(baseUri)
                        .param("page", validPageParam)
                        .param("search", invalidSearchParam)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                    .andExpect(status().isBadRequest)
            }

            @Test
            @DisplayName("given invalid search parameters - when GET entities - returns BadRequest response")
            fun testGetEntitiesWithOnlyInvalidSearchParameter() {
                doThrow(RuntimeException::class).whenever(service).findAll(search = invalidSearchParam)

                mvc.perform(
                    get(baseUri)
                        .param("search", invalidSearchParam)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                    .andExpect(status().isBadRequest)
            }

            @Test
            @DisplayName("given ONLY invalid search parameters - when COUNT entities - returns BadRequest response")
            fun testCountEntitiesWithOnlyInvalidSearchParameter() {
                doThrow(RuntimeException::class).whenever(service).count(invalidSearchParam)

                mvc.perform(
                    get(countUri)
                        .param("search", invalidSearchParam)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                    .andExpect(status().isBadRequest)
            }

            @Test
            @DisplayName("given invalid search parameters - when DELETE entities - returns BadRequest response")
            fun testDeleteEntitiesWithInvalidSearchParameter() {
                doThrow(RuntimeException::class).whenever(service).removeAll(invalidSearchParam)

                mvc.perform(
                    delete(baseUri)
                        .param("page", validPageParam)
                        .param("search", invalidSearchParam)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                    .andExpect(status().isBadRequest)
            }
        }
    }

}
