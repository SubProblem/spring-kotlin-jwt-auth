package com.subproblem.springkotlinauthentication.controller

import com.subproblem.springkotlinauthentication.dto.AuthenticationRequestDTO
import com.subproblem.springkotlinauthentication.dto.RegistrationRequestDTO
import com.subproblem.springkotlinauthentication.dto.UserResponseDTO
import com.subproblem.springkotlinauthentication.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1")
class UserController(private val userService: UserService) {


    @PostMapping("/register")
    fun register(@RequestBody request: RegistrationRequestDTO): ResponseEntity<Any> {
        return userService.registerUser(request)
    }

    @PostMapping("/login")
    fun authenticate(@RequestBody request: AuthenticationRequestDTO): ResponseEntity<Any> {
        return userService.authenticateUser(request)
    }

    @GetMapping("/user")
    fun getUserInformation(authentication: Authentication): ResponseEntity<UserResponseDTO> {
        return userService.getUserInfo(authentication)
    }
}