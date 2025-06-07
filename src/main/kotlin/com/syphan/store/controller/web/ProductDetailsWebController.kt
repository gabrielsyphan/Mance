package com.syphan.store.controller.web

import com.syphan.store.service.ProductService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.ui.Model
import java.util.UUID

@Controller
@RequestMapping("/products")
class ProductDetailsWebController(
    private val productService: ProductService
) {

    @GetMapping("/{id}")
    fun viewProduct(@PathVariable id: UUID, model: Model): String {
        val product = productService.findById(id)
        model.addAttribute("product", product)
        model.addAttribute("currentUri", "products")
        model.addAttribute("store", product.store)
        return "private/product-detail"
    }
}