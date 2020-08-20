package com.reizu.core.api.controller

import com.reizu.core.api.FullyUniqueControllable
import com.reizu.core.api.service.BaseUniqueService
import com.reizu.core.api.utility.Utility.DEFAULT_PAGE_SIZE
import com.reizu.core.api.utility.Utility.DEFAULT_SORT_FIELD
import com.reizu.core.entity.UniqueEntityable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.SortDefault
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

/**
 * Abstract controller class
 */
abstract class BaseUniqueController<T : UniqueEntityable> : FullyUniqueControllable<T> {

    @Autowired
    private lateinit var service: BaseUniqueService<T>

    @GetMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun find(@PathVariable("id") id: UUID): T {
        return service.find(id)
    }

    override fun findAllById(ids: Iterable<UUID>): List<T> {
        TODO("not implemented")
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun findAll(
        @RequestParam("page")
        @PageableDefault(size = DEFAULT_PAGE_SIZE)
        @SortDefault(sort = [DEFAULT_SORT_FIELD])
        page: Pageable?,
        @RequestParam("search")
        search: String?
    ): Iterable<T> {
        return service.findAll(page, search)
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun findAllActive(
        @RequestParam("page")
        @PageableDefault(size = DEFAULT_PAGE_SIZE)
        @SortDefault(sort = [DEFAULT_SORT_FIELD])
        page: Pageable?,
        @RequestParam("search")
        search: String?
    ): Iterable<T> {
        return service.findAllActive(page, search)
    }

    @GetMapping(path = ["/count"], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun count(
        @RequestParam("search")
        search: String?
    ): Long {
        return service.count(search)
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    override fun create(@RequestBody entity: T): T {
        return service.create(entity)
    }

    @Deprecated("PUT doesn't work generically")
    @PutMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    override fun replace(@PathVariable("id") id: UUID, @RequestBody entity: T): T {
        return service.replace(id, entity)
    }

    @Deprecated("PATCH doesn't work generically")
    @PatchMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    override fun modify(@PathVariable("id") id: UUID, @RequestBody patchedFields: Map<String, Any>): T {
        return service.modify(id, patchedFields)
    }

    @DeleteMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun remove(@PathVariable("id") id: UUID) {
        return service.remove(id)
    }

    @DeleteMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun removeAll(
        @RequestParam("search")
        search: String?
    ) {
        return service.removeAll(search)
    }

}
