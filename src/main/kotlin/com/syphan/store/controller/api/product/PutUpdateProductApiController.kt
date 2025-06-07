package com.syphan.store.controller.api.product

import com.syphan.store.controller.api.product.dto.ProductRequest
import com.syphan.store.entity.Product
import com.syphan.store.service.ProductService
import com.syphan.store.service.StoreService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID

@Controller
@RequestMapping("/api/v1/store")
class PutUpdateProductApiController(
    private val storeService: StoreService,
    private val productService: ProductService
) {

    @PutMapping("/{slug}/products/{productId}/update")
    fun updateProduct(
        @PathVariable slug: String,
        @PathVariable productId: UUID,
        @ModelAttribute productRequest: ProductRequest,
        request: HttpServletRequest
    ): ResponseEntity<Unit> {
        val store = storeService.findBySlug(slug) ?: return ResponseEntity.notFound().build()
        productService.findByIdAndStore(productId, store).let {
            val imageUrl = productRequest.imageFile?.takeIf { !it.isEmpty }?.let { file ->
                val filename = UUID.randomUUID().toString() + "-" + file.originalFilename
                val path = Paths.get("uploads", filename)
                Files.createDirectories(path.parent)
                Files.write(path, file.bytes)
                "${request.scheme}://${request.serverName}:${request.serverPort}/api/v1/store/products/image/$filename"
            } ?: productRequest.imageUrl // fallback para URL direta

            val product = Product(
                id = it.id,
                name = productRequest.name,
                description = productRequest.description,
                price = productRequest.price,
                imageUrl = imageUrl,
                available = productRequest.available,
                category = productRequest.category,
                color = productRequest.color,
                size = productRequest.size,
                gender = productRequest.gender,
                store = store
            )

            productService.save(product)
        }
        return ResponseEntity.ok().build()
    }
}