package com.subproblem.springkotlinauthentication.repository

import com.subproblem.springkotlinauthentication.entity.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.Optional

interface UserRepository: MongoRepository<User, Int> {

    fun findUserByEmail(email: String?): User?
    fun existsByEmail(email: String?): Boolean
}