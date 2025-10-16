package com.example.auth.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.login.domain.usecase.LoginUseCase
import com.example.auth.register.domain.model.UserRequest
import com.example.core.db.uitls.DataState
import com.example.core.db.uitls.asMutableStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase
): ViewModel(){
    private val _loginData =
        MutableStateFlow<DataState<Unit>>(DataState.Empty)
    val loginData = _loginData.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val req = UserRequest(
                email = email,
                password = password,
                active = false
            )
            _loginData.asMutableStateFlow {
                useCase.invoke(req)
            }
        }
    }
}