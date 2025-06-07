package com.syphan.store.controller.web.auth

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal

@Controller
@RequestMapping("/signin")
class SignInWebController {

    @GetMapping
    fun signin(principal: Principal?): String {
        if (principal != null) {
            return "redirect:/products"
        }
        return "auth/signin"
    }
}