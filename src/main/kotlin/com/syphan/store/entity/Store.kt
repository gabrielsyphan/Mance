package com.syphan.store.entity

import jakarta.persistence.*
import java.util.*

@Entity
data class Store(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "name", nullable = false, length = 100)
    val name: String,

    @Column(name = "description", nullable = false, length = 500)
    val description: String,

    @Column(name = "slug", unique = true, nullable = false, length = 100)
    val slug: String,

    @OneToMany(mappedBy = "store", cascade = [CascadeType.ALL], orphanRemoval = true)
    val products: List<Product> = listOf(),

    @OneToOne
    @JoinColumn(name = "owner_id")
    val owner: User
) {
    constructor(): this (
        name = "",
        description = "",
        slug = "",
        products = listOf(),
        owner = User()
    )
}