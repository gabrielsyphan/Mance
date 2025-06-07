package com.syphan.store.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
data class Product(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "name", nullable = false, length = 100)
    val name: String,

    @Column(name = "description", nullable = false, length = 500)
    val description: String,

    @Column(name = "price", nullable = false, precision = 19, scale = 2)
    val price: BigDecimal,

    @Column(name = "image_url", length = 255)
    val imageUrl: String? = null,

    @Column(name = "available", nullable = false)
    val available: Boolean = true,

    @Column(name = "category", length = 50)
    val category: String? = null,

    @Column(name = "color", length = 50)
    val color: String? = null,

    @Column(name = "size", length = 20)
    val size: String? = null,

    @Column(name = "gender", length = 20)
    val gender: String? = null,

    @ManyToOne
    @JoinColumn(name = "store_id")
    val store: Store
)