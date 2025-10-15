package com.telkomsel.mytelkomsel.orbit.core.design.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.telkomsel.mytelkomsel.orbit.core.design.theme.Color_FFFFFF
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BottomSheet(
    showBottomSheet: Boolean,
    onShowBottomSheet: (Boolean) -> Unit,
    onDismiss: () -> Unit = {},
    content: @Composable ColumnScope.((() -> Unit) -> DisposableHandle) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val dismiss = { action: () -> Unit ->
        coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                onShowBottomSheet(false)
                action()
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                onShowBottomSheet(false)
                onDismiss()
            },
            modifier = Modifier.navigationBarsPadding(),
            sheetState = sheetState,
            containerColor = Color_FFFFFF,
            tonalElevation = 0.dp,
            dragHandle = { DragHandle() }
        ) {
            content(dismiss)
        }
    }
}
