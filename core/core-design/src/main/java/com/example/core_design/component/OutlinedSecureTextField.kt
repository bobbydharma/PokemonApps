package com.example.core_design.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun OutlinedSecureTextField(
    state: TextFieldState,
    label: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    passwordHidden: Boolean,
    onVisibilityChange: () -> Unit,
    isError: Boolean = false,
) {
    val interactionSource = remember { MutableInteractionSource() }

    BasicSecureTextField(
        state = state,
        textObfuscationMode = if (passwordHidden) TextObfuscationMode.Hidden else TextObfuscationMode.Visible,
        modifier = modifier,
        decorator = { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = state.text.toString(),
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                label = label,
                isError = isError,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                placeholder = null,
                leadingIcon = null,
                trailingIcon = {
                    val description =
                        if (passwordHidden) "Show password" else "Hide password"
                    IconButton(onClick = onVisibilityChange) {
                        val visibilityIcon =
                            if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        Icon(imageVector = visibilityIcon, contentDescription = description)
                    }
                },
                contentPadding = OutlinedTextFieldDefaults.contentPadding(
                    start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp
                ),
                colors = OutlinedTextFieldDefaults.colors(),
                container = {
                    OutlinedTextFieldDefaults.Container(
                        enabled = true,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = OutlinedTextFieldDefaults.colors(),
                        shape = OutlinedTextFieldDefaults.shape,
                        modifier = modifier,
                        focusedBorderThickness = OutlinedTextFieldDefaults.FocusedBorderThickness,
                        unfocusedBorderThickness = OutlinedTextFieldDefaults.UnfocusedBorderThickness
                    )
                }
            )
        }
    )
}

