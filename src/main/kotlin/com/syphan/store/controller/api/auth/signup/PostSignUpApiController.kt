package com.syphan.store.controller.api.auth.signup

import com.syphan.store.controller.api.auth.signup.dto.SignupRequest
import com.syphan.store.entity.Store
import com.syphan.store.entity.User
import com.syphan.store.repository.StoreRepository
import com.syphan.store.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/auth/signup")
class PostSignUpApiController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val storeRepository: StoreRepository
) {

    @PostMapping()
    fun signup(@RequestBody dto: SignupRequest): ResponseEntity<Any> {
        if (userRepository.findByEmail(dto.email) != null) {
            return ResponseEntity.badRequest().body(mapOf("message" to "Email already in use"))
        }

        val user = User(
            id = UUID.randomUUID(),
            name = dto.name,
            email = dto.email,
            password = passwordEncoder.encode(dto.password)
        )
        userRepository.save(user)

        val store = Store(
            name = dto.storeName,
            description = "Welcome to ${dto.storeName}",
            slug = dto.storeSlug,
            owner = user
        )
        storeRepository.save(store)

        return ResponseEntity.ok(mapOf("message" to "Signup successful"))
    }
}