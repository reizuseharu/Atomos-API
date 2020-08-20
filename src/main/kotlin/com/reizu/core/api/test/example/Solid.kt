package com.reizu.core.api.test.example

import com.reizu.core.entity.BaseSerialEntity
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Solid entity class
 *
 * @property id Unique identifier for entity
 * @property createdOn When entity's entry was created
 * @property modifiedOn When entity's entry was last modified
 * @property removedOn When entity's entry was removed
 */
@Entity
@Table(name = "`Solid`")
data class Solid(private val name: String = "Rock") : BaseSerialEntity()
