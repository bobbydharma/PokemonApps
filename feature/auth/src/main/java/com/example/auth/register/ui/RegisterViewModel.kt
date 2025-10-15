package com.example.auth.register.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.register.domain.model.UserRequest
import com.example.auth.register.domain.usecase.RegisterUseCase
import com.example.core.db.uitls.DataState
import com.example.core.db.uitls.asMutableStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCase: RegisterUseCase
) : ViewModel() {
    private val _registerData =
        MutableStateFlow<DataState<Unit>>(DataState.Empty)
    val registerData = _registerData.asStateFlow()

    fun postRegister(email: String, password: String) {
        viewModelScope.launch {
            val req = UserRequest(
                username = email,
                password = password,
                active = false
            )
            _registerData.asMutableStateFlow {
                useCase.invoke(req)
            }
        }
    }
}