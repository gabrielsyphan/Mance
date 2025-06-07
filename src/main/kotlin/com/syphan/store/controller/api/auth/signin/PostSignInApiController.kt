package com.syphan.store.controller.api.auth.signin

import com.syphan.store.controller.api.auth.signin.dto.SignInRequest
import com.syphan.store.repository.UserRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth/signin")
class PostSignInApiController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping
    fun signin(
        request: HttpServletRequest,
        response: HttpServletResponse,
        @RequestBody dto: SignInRequest
    ): ResponseEntity<Any> {
        val user = userRepository.findByEmail(dto.email)
            ?: return ResponseEntity.status(401).body(mapOf("message" to "Invalid credentials"))

        if (!passwordEncoder.matches(dto.password, user.password)) {
            return ResponseEntity.status(401).body(mapOf("message" to "Invalid credentials"))
        }

        request.login(dto.email, dto.password)
        return ResponseEntity.ok(mapOf("message" to "Login successful"))
    }
}