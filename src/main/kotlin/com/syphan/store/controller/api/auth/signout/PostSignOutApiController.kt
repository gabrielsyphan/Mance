package com.syphan.store.controller.api.auth.signout

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth/signout")
class PostSignOutApiController {

    @PostMapping()
    fun logout(request: HttpServletRequest): ResponseEntity<Any> {
        request.logout()
        return ResponseEntity.ok(mapOf("message" to "Logged out"))
    }
}
