package com.reizu.core.api.test.example

import com.reizu.core.api.service.BaseSerialService
import org.springframework.stereotype.Service

/**
 * Solid service class
 */
@Service
class SolidService : BaseSerialService<Solid>(Solid::class.java)
