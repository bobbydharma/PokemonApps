package com.example.auth.register.data.mapper

import com.example.auth.register.domain.model.UserDomainModel
import com.example.core.db.entity.UserEntity

fun UserEntity.toDomainModel(): UserDomainModel =
    UserDomainModel(
        email = email,
        passwordHash =password,
        active = active,
        name = name,
        address = address,
        gender = gender
    )