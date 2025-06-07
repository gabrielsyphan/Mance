package com.syphan.store.repository

import com.syphan.store.entity.Store
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID


interface StoreRepository : JpaRepository<Store, UUID> {
    fun findBySlug(slug: String): Store?
    fun findByOwnerId(ownerId: UUID): Store?
}