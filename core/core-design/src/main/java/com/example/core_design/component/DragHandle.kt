package com.telkomsel.mytelkomsel.orbit.core.design.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.telkomsel.mytelkomsel.orbit.core.design.theme.Color_E9E8ED
import com.telkomsel.mytelkomsel.orbit.core.design.theme.OrbitTheme

@Composable
internal fun DragHandle(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.padding(top = 8.dp, bottom = 16.dp),
        color = Color_E9E8ED,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Box(
            Modifier
                .size(
                    width = 44.dp,
                    height = 6.dp
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDragHandle() {
    OrbitTheme {
        DragHandle()
    }
}
