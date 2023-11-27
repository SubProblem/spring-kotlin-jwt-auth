package com.subproblem.springkotlinauthentication.dto

data class UserResponseDTO(
    var id: String,
    var firstname: String,
    val lastname: String,
    var age: Int,
    var email: String,
    var role: String
)