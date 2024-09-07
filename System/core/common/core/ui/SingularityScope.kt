/*
 * Copyright (c) 2024 Singularity Indonesia
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import core.layer.SystemLoggerScope
import core.layer.SystemLoggerScopeImpl

@Immutable
data class SingularityScope(
    val unit: Unit = Unit,
) : SystemLoggerScope by SystemLoggerScopeImpl() {
    @Composable
    fun SingularityScopeCompose(content: @Composable SingularityScope.() -> Unit) {
        content()
    }
}
