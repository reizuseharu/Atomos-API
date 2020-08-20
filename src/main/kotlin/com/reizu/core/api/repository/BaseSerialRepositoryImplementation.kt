package com.reizu.core.api.repository

import com.reizu.core.entity.BaseSerialEntity
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import javax.persistence.EntityManager

/**
 * Base Repository implementation class
 *
 * @property T Entity type being manipulated
 */
abstract class BaseSerialRepositoryImplementation<T : BaseSerialEntity>(domainClass: Class<T>, entityManager: EntityManager)
    : SimpleJpaRepository<T, Long>(domainClass, entityManager), BaseSerialRepository<T>
