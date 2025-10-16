package com.example.auth.register.domain.mapper

import com.example.auth.register.domain.model.UserRequest
import com.example.core.db.entity.UserEntity

fun UserRequest.toEntity(): UserEntity {
    return UserEntity(
        email,
        password,
        name,
        gender,
        address,
        active
    )
}