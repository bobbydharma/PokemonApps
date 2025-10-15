package com.telkomsel.mytelkomsel.orbit.core.design.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.telkomsel.mytelkomsel.orbit.core.design.theme.OrbitTheme

@Composable
internal fun ProgressDialog() {
    val dialogProperties =
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)

    Dialog(
        onDismissRequest = {},
        properties = dialogProperties
    ) {
        val infiniteTransition = rememberInfiniteTransition(label = INFINITE_TRANSITION)
        val rotation by infiniteTransition.animateFloat(
            initialValue = INITIAL_VALUE,
            targetValue = TARGET_VALUE,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = DURATION_MILLIS, easing = LinearEasing)
            ),
            label = ROTATION
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

private const val INFINITE_TRANSITION = "infiniteTransition"
private const val ROTATION = "rotation"
private const val INITIAL_VALUE = 0f
private const val TARGET_VALUE = 360f
private const val DURATION_MILLIS = 1000

@Preview(showBackground = true)
@Composable
private fun PreviewProgressDialog() {
    OrbitTheme {
        ProgressDialog()
    }
}
