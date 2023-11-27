package com.subproblem.springkotlinauthentication.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType

@Document
class User {
    @Id
    lateinit var id: String

    var firstname: String

    val lastname: String

    var age: Int = 0

    @Indexed(unique = true)
    var email: String

    var password: String

    @Field(targetType = FieldType.STRING)
    var role: Role


    constructor(firstname: String, lastname: String, age: Int, email: String, password: String, role: Role) {
        this.firstname = firstname
        this.lastname = lastname
        this.age = age
        this.email = email
        this.password = password
        this.role = role
    }
}
