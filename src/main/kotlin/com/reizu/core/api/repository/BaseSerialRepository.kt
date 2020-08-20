package com.reizu.core.api.repository

import com.reizu.core.entity.SerialEntityable

/**
 * Base Repository interface
 *
 * @property T Entity type being manipulated
 */
interface BaseSerialRepository<T : SerialEntityable> : BaseRepository<T, Long>
