package com.syphan.store.controller.api.auth.signup.dto

data class SignupRequest(
    val name: String,
    val email: String,
    val password: String,
    val storeName: String,
    val storeSlug: String
)