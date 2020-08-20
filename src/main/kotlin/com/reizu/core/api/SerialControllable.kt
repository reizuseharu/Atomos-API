package com.reizu.core.api

import com.reizu.core.entity.SerialEntityable

/**
 * Interface which supports all standard CRUD actions for serial entities
 */
interface SerialControllable<T : SerialEntityable> : Postable<T>, SerialGetable<T>, SerialPutable<T>, SerialDeletable<T>
