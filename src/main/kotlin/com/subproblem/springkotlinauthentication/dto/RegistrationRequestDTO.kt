package com.subproblem.springkotlinauthentication.dto

data class RegistrationRequestDTO(
    val firstname: String,
    val lastname: String,
    val age: Int,
    val email: String,
    val password: String,
    val role: String
)
