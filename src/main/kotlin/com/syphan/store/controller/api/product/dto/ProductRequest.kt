package com.syphan.store.controller.api.product.dto

import org.springframework.web.multipart.MultipartFile
import java.math.BigDecimal

data class ProductRequest(
    val name: String,
    val description: String,
    val price: BigDecimal,
    val available: Boolean = false,
    val category: String?,
    val color: String?,
    val size: String?,
    val gender: String?,
    val imageUrl: String?,
    val imageFile: MultipartFile?
)
