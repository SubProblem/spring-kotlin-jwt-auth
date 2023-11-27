package com.subproblem.springkotlinauthentication.config

import com.subproblem.springkotlinauthentication.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userRepository: UserRepository): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findUserByEmail(username)

        return user?.let {
            CustomUserDetails(it)
        } ?: throw UsernameNotFoundException("User not found")
    }
}