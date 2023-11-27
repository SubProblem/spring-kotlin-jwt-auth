package com.subproblem.springkotlinauthentication.entity

import com.subproblem.springkotlinauthentication.entity.Permissions.*
import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class Role(private val permissions: Set<Permissions>) {

    USER(
     setOf(
         USER_CREATE,
         USER_READ,
         USER_UPDATE,
         USER_DELETE
     )
    ),
    ADMIN(
        setOf(
            ADMIN_READ,
            ADMIN_CREATE,
            ADMIN_UPDATE,
            ADMIN_DELETE,
            USER_CREATE,
            USER_READ,
            USER_UPDATE,
            USER_DELETE
        )
    );

    fun getAuthorities(): MutableList<SimpleGrantedAuthority> {
        val authorities = permissions
            .map { permission -> SimpleGrantedAuthority(permission.getPermission()) }
            .toMutableList()

        authorities.add(SimpleGrantedAuthority("ROLE_${this.name}"))

        return authorities
    }

}