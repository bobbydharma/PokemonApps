package com.telkomsel.mytelkomsel.orbit.core.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_design.R
import com.telkomsel.mytelkomsel.orbit.core.design.theme.Color_0050AE
import com.telkomsel.mytelkomsel.orbit.core.design.theme.Color_479CFF
import com.telkomsel.mytelkomsel.orbit.core.design.theme.OrbitTheme

@Composable
internal fun Notice(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color_479CFF.copy(alpha = 0.15f), RoundedCornerShape(16.dp))
            .padding(12.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_info_outline_24),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = Color_0050AE
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNotice() {
    OrbitTheme {
        Notice(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        )
    }
}
