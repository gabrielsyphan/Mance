package com.syphan.store.service

import com.syphan.store.entity.Store
import com.syphan.store.repository.StoreRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class StoreService(private val storeRepository: StoreRepository) {
    fun findAll(): List<Store> = storeRepository.findAll()

    fun findByOwnerId(ownerId: UUID): Store? = storeRepository.findByOwnerId(ownerId)

    fun findBySlug(slug: String): Store? = storeRepository.findBySlug(slug)

    fun save(store: Store): Store = storeRepository.save(store)
}