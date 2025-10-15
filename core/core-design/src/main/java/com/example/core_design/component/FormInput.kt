package com.telkomsel.mytelkomsel.orbit.core.design.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_design.R
import com.telkomsel.mytelkomsel.orbit.core.design.theme.Color_757F90
import com.telkomsel.mytelkomsel.orbit.core.design.theme.OrbitTheme

@Composable
internal fun FormInput(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.fillMaxWidth(),
            color = Color_757F90,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextFieldCustom(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .fillMaxWidth()
                .height(44.dp),
            enabled = enabled,
            readOnly = readOnly,
            textStyle = MaterialTheme.typography.bodySmall,
            trailingIcon = if (readOnly) {
                {
                    Icon(
                        painter = painterResource(R.drawable.baseline_keyboard_arrow_down_24),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onClick() }
                    )
                }
            } else {
                null
            },
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            shape = RoundedCornerShape(12.dp),
            colors = textFieldColors(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFormInput() {
    OrbitTheme {
        FormInput(
            label = "Label",
            value = "Value",
            onValueChange = {}
        )
    }
}
