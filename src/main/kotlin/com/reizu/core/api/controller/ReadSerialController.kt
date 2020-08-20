package com.reizu.core.api.controller

import com.reizu.core.api.Countable
import com.reizu.core.api.SerialGetable
import com.reizu.core.api.service.BaseSerialService
import com.reizu.core.api.utility.Utility
import com.reizu.core.entity.SerialEntityable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.SortDefault
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * Abstract read only controller class
 */
abstract class ReadSerialController<T : SerialEntityable> : SerialGetable<T>, Countable {

    @Autowired
    private lateinit var service: BaseSerialService<T>

    @GetMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun find(id: Long): T {
        return service.find(id)
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun findAll(
        @RequestParam("page")
        @PageableDefault(size = Utility.DEFAULT_PAGE_SIZE)
        @SortDefault(sort = [Utility.DEFAULT_SORT_FIELD])
        page: Pageable?,
        @RequestParam("search")
        search: String?
    ): Iterable<T> {
        return service.findAll(page, search)
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun findAllActive(
        @RequestParam("page")
        @PageableDefault(size = Utility.DEFAULT_PAGE_SIZE)
        @SortDefault(sort = [Utility.DEFAULT_SORT_FIELD])
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

}
