package com.telkomsel.mytelkomsel.orbit.core.design.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_design.R
import com.telkomsel.mytelkomsel.orbit.core.design.theme.Color_FFFFFF
import com.telkomsel.mytelkomsel.orbit.core.design.theme.OrbitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    title: String,
    color: Color = Color_FFFFFF,
    isBack: Boolean = true,
    onBack: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        navigationIcon = {
            if (isBack) {
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_24),
                        contentDescription = null
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color
        ),
        expandedHeight = 56.dp
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewTopBar() {
    OrbitTheme {
        TopBar(title = "Title")
    }
}
