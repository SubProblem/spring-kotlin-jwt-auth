package com.subproblem.springkotlinauthentication.entity

enum class Permissions(private val permission: String) {

    ADMIN_READ("read"),
    ADMIN_CREATE("create"),
    ADMIN_UPDATE("update"),
    ADMIN_DELETE("delete"),

    USER_READ("read"),
    USER_CREATE("create"),
    USER_UPDATE("update"),
    USER_DELETE("delete");

    fun getPermission(): String {
        return permission;
    }
}