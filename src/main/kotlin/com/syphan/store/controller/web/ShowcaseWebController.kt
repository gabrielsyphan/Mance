package com.syphan.store.controller.web

import com.syphan.store.service.ProductService
import com.syphan.store.service.StoreService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/showcase/store")
class ShowcaseWebController(
    private val storeService: StoreService,
    private val productService: ProductService
) {

    @GetMapping("/{slug}")
    fun viewStore(
        @PathVariable slug: String,
        model: Model
    ): String {
        val store = storeService.findBySlug(slug)
            ?: return "redirect:/"
        val products = productService.listByStoreId(store.id)

        model.addAttribute("store", store)
        model.addAttribute("products", products)
        return "public/showcase"
    }
}