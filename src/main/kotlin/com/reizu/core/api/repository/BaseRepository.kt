package com.reizu.core.api.repository

import com.reizu.core.entity.Entityable
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation

/**
 * Base Repository interface
 *
 * @property T Entity type being manipulated
 */
interface BaseRepository<T : Entityable, V: Any> : JpaRepositoryImplementation<T, V>
