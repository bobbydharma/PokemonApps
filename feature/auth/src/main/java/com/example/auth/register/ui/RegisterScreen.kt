package com.example.auth.register.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.core.db.uitls.DataState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    toLogin: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val registerState by viewModel.registerData.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val emailFocusRequester = remember { FocusRequester() }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Laki-laki") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(registerState) {
        when (registerState) {
            is DataState.Success -> {
                name = ""
                email = ""
                password = ""
                address = ""
                gender = "Laki-laki"
                emailError = null
                passwordError = null
                emailFocusRequester.requestFocus()

                snackbarHostState.showSnackbar("Registrasi Berhasil")
                viewModel.resetState()
            }

            is DataState.Error -> {
                val message =
                    (registerState as DataState.Error).exception.message ?: "Registrasi gagal"
                snackbarHostState.showSnackbar(message)
                viewModel.resetState()
            }

            else -> Unit
        }
    }

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = { Text("Register", maxLines = 1, overflow = TextOverflow.Ellipsis) }
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        RegisterContent(
            modifier = Modifier.padding(padding),
            registerState = registerState,
            emailFocusRequester = emailFocusRequester,
            name = name,
            email = email,
            password = password,
            gender = gender,
            address = address,
            emailError = emailError,
            passwordError = passwordError,
            onNameChange = { name = it },
            onEmailChange = {
                email = it
                emailError = null
            },
            onPasswordChange = {
                password = it
                passwordError = null
            },
            onGenderChange = { gender = it },
            onAddressChange = { address = it },
            onRegister = {
                var valid = true
                if (email.isBlank()) {
                    emailError = "Email tidak boleh kosong"
                    valid = false
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailError = "Format email tidak valid"
                    valid = false
                }

                if (password.isBlank()) {
                    passwordError = "Password tidak boleh kosong"
                    valid = false
                } else if (password.length < 6) {
                    passwordError = "Password minimal 6 karakter"
                    valid = false
                }

                if (valid) {
                    scope.launch {
                        viewModel.postRegister(name, email, password, gender, address)
                    }
                }
            },
            toLogin = toLogin
        )
    }
}

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    registerState: DataState<*>?,
    emailFocusRequester: FocusRequester,
    name: String,
    email: String,
    password: String,
    gender: String,
    address: String,
    emailError: String?,
    passwordError: String?,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onGenderChange: (String) -> Unit,
    onAddressChange: (String) -> Unit,
    onRegister: () -> Unit,
    toLogin: () -> Unit
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Nama") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            singleLine = true,
            isError = emailError != null,
            supportingText = {
                if (emailError != null) Text(emailError, color = MaterialTheme.colorScheme.error)
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(emailFocusRequester)
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle Password"
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = passwordError != null,
            supportingText = {
                if (passwordError != null) Text(passwordError, color = MaterialTheme.colorScheme.error)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Jenis Kelamin", style = MaterialTheme.typography.bodyMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = gender == "Laki-laki",
                onClick = { onGenderChange("Laki-laki") }
            )
            Text("Laki-laki", modifier = Modifier.padding(end = 16.dp))
            RadioButton(
                selected = gender == "Perempuan",
                onClick = { onGenderChange("Perempuan") }
            )
            Text("Perempuan")
        }

        OutlinedTextField(
            value = address,
            onValueChange = onAddressChange,
            label = { Text("Alamat") },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 80.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        when (registerState) {
            is DataState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            else -> Button(
                onClick = onRegister,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text("Register")
            }
        }

        OutlinedButton(
            onClick = toLogin,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Login")
        }
    }
}

@SuppressLint("RememberInComposition")
@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    RegisterContent(
        registerState = null,
        emailFocusRequester = FocusRequester(),
        name = "",
        email = "",
        password = "",
        gender = "Laki-laki",
        address = "",
        emailError = null,
        passwordError = null,
        onNameChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        onGenderChange = {},
        onAddressChange = {},
        onRegister = {},
        toLogin = {}
    )
}