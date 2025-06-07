package com.syphan.store.service

import com.syphan.store.entity.User
import com.syphan.store.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun register(user: User): User {
        if (userRepository.findByEmail(user.email) != null) {
            throw IllegalArgumentException("Email already registered")
        }
        return userRepository.save(user)
    }

    fun authenticate(email: String, password: String): User? {
        val user = userRepository.findByEmail(email)
        return if (user != null && user.password == password) user else null
    }

    fun findById(id: UUID): User? = userRepository.findById(id).orElse(null)
}
