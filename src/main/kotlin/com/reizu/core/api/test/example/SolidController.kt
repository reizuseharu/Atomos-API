package com.reizu.core.api.test.example

import com.reizu.core.api.controller.BaseSerialController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Example controller class
 */
@RestController
@RequestMapping("/solid")
class SolidController : BaseSerialController<Solid>()
