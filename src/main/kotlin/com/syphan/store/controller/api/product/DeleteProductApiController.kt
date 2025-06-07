package com.syphan.store.controller.api.product

import com.syphan.store.service.ProductService
import com.syphan.store.service.StoreService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

@Controller
@RequestMapping("/api/v1/store")
class DeleteProductApiController(
    private val storeService: StoreService,
    private val productService: ProductService
) {

    @DeleteMapping("/{slug}/products/{productId}/delete")
    fun updateProduct(
        @PathVariable slug: String,
        @PathVariable productId: UUID
    ): ResponseEntity<Unit> {
        val store = storeService.findBySlug(slug) ?: return ResponseEntity.notFound().build()
        productService.findByIdAndStore(productId, store).let {
            productService.deleteById(it.id)
        }
        return ResponseEntity.ok().build()
    }
}