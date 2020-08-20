package com.reizu.core.api

import com.reizu.core.entity.SerialEntityable

/**
 * Interface which supports all CRUD actions for serial entities
 */
interface FullySerialControllable<T : SerialEntityable> : Postable<T>, SerialGetable<T>, SerialPutable<T>, SerialPatchable<T>, SerialDeletable<T>, Countable
