package com.telkomsel.mytelkomsel.orbit.core.design.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.telkomsel.mytelkomsel.orbit.core.design.theme.Color_9CA9B9
import com.telkomsel.mytelkomsel.orbit.core.design.theme.Color_DAE0E9
import com.telkomsel.mytelkomsel.orbit.core.design.theme.Color_ED0226
import com.telkomsel.mytelkomsel.orbit.core.design.theme.Color_FFFFFF
import com.telkomsel.mytelkomsel.orbit.core.design.theme.OrbitTheme

@Composable
internal fun LabelButton(
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = Color_ED0226,
    fontWeight: FontWeight = FontWeight.Normal,
    style: TextStyle = MaterialTheme.typography.bodySmall,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.height(18.dp),
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(contentColor = color),
        contentPadding = PaddingValues(1.dp)
    ) {
        Text(
            text = label,
            fontWeight = fontWeight,
            style = style
        )
    }
}

@Composable
internal fun PrimaryButton(
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    fontWeight: FontWeight = FontWeight.Normal,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color_ED0226,
            contentColor = Color_FFFFFF,
            disabledContainerColor = Color_DAE0E9,
            disabledContentColor = Color_9CA9B9
        ),
        border = null
    ) {
        Text(
            text = label,
            fontWeight = fontWeight,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
internal fun SecondaryButton(
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    fontWeight: FontWeight = FontWeight.Normal,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        enabled = enabled,
        border = BorderStroke(1.dp, if (enabled) Color_ED0226 else Color_DAE0E9)
    ) {
        Text(
            text = label,
            fontWeight = fontWeight,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
internal fun PrimaryButtonSmall(
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    fontWeight: FontWeight = FontWeight.Normal,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(36.dp),
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color_ED0226,
            contentColor = Color_FFFFFF,
            disabledContainerColor = Color_DAE0E9,
            disabledContentColor = Color_9CA9B9
        ),
        border = null,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = label,
            fontWeight = fontWeight,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
internal fun SecondaryButtonSmall(
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    fontWeight: FontWeight = FontWeight.Normal,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(36.dp),
        enabled = enabled,
        border = BorderStroke(1.dp, if (enabled) Color_ED0226 else Color_DAE0E9),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = label,
            fontWeight = fontWeight,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLabelButton() {
    OrbitTheme {
        LabelButton("Label") {}
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPrimaryButton() {
    OrbitTheme {
        PrimaryButton("Label") {}
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSecondaryButton() {
    OrbitTheme {
        SecondaryButton("Label") {}
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPrimaryButtonSmall() {
    OrbitTheme {
        PrimaryButtonSmall("Label") {}
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSecondaryButtonSmall() {
    OrbitTheme {
        SecondaryButtonSmall("Label") {}
    }
}
