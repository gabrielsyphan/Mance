package com.syphan.store.controller.web

import com.syphan.store.repository.UserRepository
import com.syphan.store.service.ProductService
import com.syphan.store.service.StoreService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal

@Controller
@RequestMapping("/products")
class ProductsWebController(
    private val userRepository: UserRepository,
    private val storeService: StoreService,
    private val productService: ProductService
) {

    @GetMapping
//    fun list(principal: Principal, model: Model): String {
//        val user = userRepository.findByEmail(principal.name) ?: return "redirect:/signin"
    fun list(model: Model): String {
        val user = userRepository.findByEmail("lucasgabrielpdoliveira@gmail.com") ?: return "redirect:/signin"
        val store = storeService.findByOwnerId(user.id) ?: return "redirect:/signup"
        val products = productService.listByStoreId(store.id)

        model.addAttribute("store", store)
        model.addAttribute("products", products)
        model.addAttribute("currentUri", "products")
        return "private/products"
    }
}
