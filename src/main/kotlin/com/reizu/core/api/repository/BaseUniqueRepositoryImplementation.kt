package com.reizu.core.api.repository

import com.reizu.core.entity.UniqueEntityable
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import java.util.UUID
import javax.persistence.EntityManager

/**
 * Base Repository implementation class
 *
 * @property T Entity type being manipulated
 */
abstract class BaseUniqueRepositoryImplementation<T : UniqueEntityable>(domainClass: Class<T>, entityManager: EntityManager)
    : SimpleJpaRepository<T, UUID>(domainClass, entityManager), BaseUniqueRepository<T>
