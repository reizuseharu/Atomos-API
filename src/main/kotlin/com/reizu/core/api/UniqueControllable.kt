package com.reizu.core.api

import com.reizu.core.entity.UniqueEntityable

/**
 * Interface which supports all standard CRUD actions for unique id entities
 */
interface UniqueControllable<T : UniqueEntityable> : Postable<T>, UniqueGetable<T>, UniquePutable<T>, UniqueDeletable<T>
