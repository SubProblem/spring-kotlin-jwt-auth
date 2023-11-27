package com.subproblem.springkotlinauthentication.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwt
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import javax.crypto.SecretKey


@Component
class JwtService(private val customUserDetailsService: UserDetailsService) {

    @Value("\${jwt.secret}")
    lateinit var jwtSecret: String
    @Value("\${jwt.duration}")
    val duration: Int = 0


    private val secretKey: Key by lazy {
        Keys.hmacShaKeyFor(jwtSecret.toByteArray())
    }


    fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
    }

    fun generateJwtToken(
        userDetails: UserDetails,
        extraClaims: Map<String, Any> = emptyMap()
    ): String = Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.username)
        .setIssuedAt(Date(System.currentTimeMillis()))
        .setExpiration(Date(System.currentTimeMillis() + duration))
        .signWith(secretKey, SignatureAlgorithm.HS256)
        .compact()

    fun generateJwtToken(userDetails: UserDetails):
            String = generateJwtToken(userDetails, emptyMap())


    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val email = extractUserEmail(token)
        return userDetails.username == email && !isExpired(token)
    }

    fun extractUserEmail(token: String): String? =
        extractAllClaims(token).subject

    fun isExpired(token: String): Boolean =
        extractAllClaims(token)
            .expiration
            .before((Date(System.currentTimeMillis())))


}