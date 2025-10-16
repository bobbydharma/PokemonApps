package com.example.auth.login.domain.usecase

import com.example.auth.login.domain.repository.LoginRepository
import com.example.auth.register.domain.model.UserRequest
import com.example.core.db.entity.UserEntity
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repo: LoginRepository
) {
    suspend operator fun invoke(user: UserRequest) {
        repo.login(
            UserEntity(
                email = user.email,
                password = user.password,
                name = user.name,
                gender = user.gender,
                address = user.address,
                active = true
            )
        )
    }
}