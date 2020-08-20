package com.reizu.core.api.repository

import com.reizu.core.entity.UniqueEntityable
import java.util.UUID

/**
 * Base Repository interface
 *
 * @property T Entity type being manipulated
 */
interface BaseUniqueRepository<T : UniqueEntityable> : BaseRepository<T, UUID>
