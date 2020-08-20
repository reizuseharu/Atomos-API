package com.reizu.core.api.controller

import com.reizu.core.api.UniqueWritable
import com.reizu.core.api.service.BaseUniqueService
import com.reizu.core.entity.UniqueEntityable
import org.springframework.beans.factory.annotation.Autowired
import java.util.UUID

/**
 * Abstract write only controller class
 */
abstract class WriteUniqueController<T : UniqueEntityable> : UniqueWritable<T> {

    @Autowired
    private lateinit var service: BaseUniqueService<T>

    override fun create(entity: T): T {
        return service.create(entity)
    }

    @Deprecated("PUT doesn't work generically")
    override fun replace(id: UUID, entity: T): T {
        return service.replace(id, entity)
    }

    @Deprecated("PATCH doesn't work generically")
    override fun modify(id: UUID, patchedFields: Map<String, Any>): T {
        return service.modify(id, patchedFields)
    }

}
