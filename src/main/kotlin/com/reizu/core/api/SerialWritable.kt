package com.reizu.core.api

import com.reizu.core.entity.SerialEntityable

/**
 * Interface which supports all CU actions for serial entities
 */
interface SerialWritable<T : SerialEntityable> : Postable<T>, SerialPutable<T>, SerialPatchable<T>
