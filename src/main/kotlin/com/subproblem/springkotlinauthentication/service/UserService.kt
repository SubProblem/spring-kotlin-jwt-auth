package com.subproblem.springkotlinauthentication.service

import com.subproblem.springkotlinauthentication.config.CustomUserDetails
import com.subproblem.springkotlinauthentication.config.CustomUserDetailsService
import com.subproblem.springkotlinauthentication.dto.AuthenticationRequestDTO
import com.subproblem.springkotlinauthentication.dto.RegistrationRequestDTO
import com.subproblem.springkotlinauthentication.dto.UserResponseDTO
import com.subproblem.springkotlinauthentication.entity.Role
import com.subproblem.springkotlinauthentication.entity.User
import com.subproblem.springkotlinauthentication.jwt.JwtService
import com.subproblem.springkotlinauthentication.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val customUserDetailsService: CustomUserDetailsService,
    private val jwtService: JwtService
) {


    fun getUserInfo(authentication: Authentication): ResponseEntity<UserResponseDTO> {

        val principal = authentication.principal

        val user = if (principal is CustomUserDetails) {
            userRepository.findUserByEmail(principal.username)
                ?: throw UsernameNotFoundException("User not found")
        } else {
            throw UsernameNotFoundException("Principal is not of UserDetails type")
        }

        val response = UserResponseDTO(
            id = user.id,
            firstname = user.firstname,
            lastname = user.lastname,
            age = user.age,
            email = user.email,
            role = user.role.name
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    fun registerUser(request: RegistrationRequestDTO): ResponseEntity<Any> {

        if (userRepository.existsByEmail(request.email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already used")
        }

        val user = User(
            firstname = request.firstname,
            lastname = request.lastname,
            age = request.age,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            role = Role.valueOf(request.role.uppercase())
        )

        userRepository.save(user)

        return ResponseEntity.status(HttpStatus.CREATED).body(user)
    }

    fun authenticateUser(request: AuthenticationRequestDTO): ResponseEntity<Any> {

        val email = request.email
        val password = request.password
        val user = customUserDetailsService.loadUserByUsername(email)
        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(email, password)

        authenticationManager.authenticate(usernamePasswordAuthenticationToken)

        val token = jwtService.generateJwtToken(user)

        return ResponseEntity.status(HttpStatus.OK).body(token)
    }
}