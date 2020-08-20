package com.reizu.core.api

import com.reizu.core.entity.UniqueEntityable

/**
 * Interface which supports all CRUD actions for unique id entities
 */
interface FullyUniqueControllable<T : UniqueEntityable> : Postable<T>, UniqueGetable<T>, UniquePutable<T>, UniquePatchable<T>, UniqueDeletable<T>, Countable
