/*
 * Copyright (c) 2024 Singularity Indonesia
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package core.ui.designsystem.atom

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import core.SystemLogger

@Composable
fun SFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = FloatingActionButtonDefaults.shape,
    containerColor: Color = FloatingActionButtonDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    val systemLogger = SystemLogger.current

    FloatingActionButton(
        onClick = {
            systemLogger.log("FloatingActionButton clicked $content")
            onClick()
        },
        modifier = modifier,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        elevation = elevation,
        interactionSource = interactionSource,
        content = content,
    )
}
