package com.syphan.store.controller.web

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class HomeWebController {

    @GetMapping
    fun home(model: Model): String {
        val authentication = SecurityContextHolder.getContext().authentication
        val isAuthenticated = authentication != null && authentication.isAuthenticated && authentication.name != "anonymousUser"
        model.addAttribute("isAuthenticated", isAuthenticated)
        return "public/home"
    }
}