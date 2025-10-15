package com.example.auth.register.domain.usecase

import com.example.auth.register.domain.mapper.toEntity
import com.example.auth.register.domain.model.UserRequest
import com.example.auth.register.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repo: RegisterRepository
) {
    suspend operator fun invoke(user: UserRequest) {
        return repo.register(
            user.toEntity()
        )
    }
}