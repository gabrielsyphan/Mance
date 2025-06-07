package com.syphan.store.entity

import jakarta.persistence.*
import java.util.*

@Entity
data class User(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "name", nullable = false, length = 100)
    val name: String,

    @Column(name = "email", unique = true, nullable = false, length = 255)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @OneToOne(mappedBy = "owner", cascade = [CascadeType.ALL])
    val store: Store? = null
) {
    constructor() : this(
        id = UUID.randomUUID(),
        name = "",
        email = "",
        password = "",
        store = null
    )
}
