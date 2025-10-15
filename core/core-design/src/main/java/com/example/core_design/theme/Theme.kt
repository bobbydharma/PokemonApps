package com.telkomsel.mytelkomsel.orbit.core.design.theme

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun OrbitTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightScheme,
        typography = typography,
        content = {
            Surface(modifier = Modifier.navigationBarsPadding()) {
                content()
            }
        }
    )
}
