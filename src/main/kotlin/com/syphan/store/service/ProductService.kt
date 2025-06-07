package com.syphan.store.service

import com.syphan.store.entity.Product
import com.syphan.store.entity.Store
import com.syphan.store.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(private val productRepository: ProductRepository) {
    fun save(product: Product): Product = productRepository.save(product)

    fun deleteById(id: UUID) = productRepository.deleteById(id)

    fun listByStoreId(storeId: UUID): List<Product> =
        productRepository.findAllByStoreId(storeId)

    fun findByIdAndStore(productId: UUID, store: Store): Product {
        return productRepository.findByIdAndStore(productId, store)
            ?: throw NoSuchElementException("Product with ID $productId not found in store ${store.name}")
    }

    fun findById(id: UUID): Product {
        return productRepository.findById(id).orElseThrow { NoSuchElementException("Product with ID $id not found") }
    }
}