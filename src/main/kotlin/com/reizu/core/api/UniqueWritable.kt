package com.reizu.core.api

import com.reizu.core.entity.UniqueEntityable

/**
 * Interface which supports all CU actions for unique id entities
 */
interface UniqueWritable<T : UniqueEntityable> : Postable<T>, UniquePutable<T>, UniquePatchable<T>
