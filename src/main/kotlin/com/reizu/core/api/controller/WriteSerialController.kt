package com.reizu.core.api.controller

import com.reizu.core.api.SerialWritable
import com.reizu.core.api.service.BaseSerialService
import com.reizu.core.entity.SerialEntityable
import org.springframework.beans.factory.annotation.Autowired

/**
 * Abstract write only controller class
 */
abstract class WriteSerialController<T : SerialEntityable> : SerialWritable<T> {

    @Autowired
    private lateinit var service: BaseSerialService<T>

    override fun create(entity: T): T {
        return service.create(entity)
    }

    @Deprecated("PUT doesn't work generically")
    override fun replace(id: Long, entity: T): T {
        return service.replace(id, entity)
    }

    @Deprecated("PATCH doesn't work generically")
    override fun modify(id: Long, patchedFields: Map<String, Any>): T {
        return service.modify(id, patchedFields)
    }

}
