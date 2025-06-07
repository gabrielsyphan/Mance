package com.syphan.store.repository

import com.syphan.store.entity.Product
import com.syphan.store.entity.Store
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProductRepository : JpaRepository<Product, UUID> {
    fun findAllByStoreId(storeId: UUID): List<Product>
    fun findByIdAndStore(productId: UUID, store: Store): Product?
}