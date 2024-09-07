package main.protocol

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import core.exception.AccessControlException
import core.operation.SystemResult
import core.protocol.AccessControl
import core.ui.SingularityScope
import core.ui.designsystem.component.STextTitle
import kotlinx.coroutines.flow.MutableStateFlow

class AttributeBasedAccessControl : AccessControl<AccessControlException> {
    private val _fallBack = MutableStateFlow<AccessControlException?>(null)
    override val fallBack = _fallBack

    override suspend fun <T> invoke(request: () -> SystemResult<T>): SystemResult<T> {
        TODO("Not yet implemented")
    }
}

context(SingularityScope)
@Composable
fun AttributeBasedAccessControl(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val accessControl = remember { AttributeBasedAccessControl() }

    content.invoke()

    // Fall Back Access Control
    val requiredAccess by accessControl.fallBack.collectAsState()
    if (requiredAccess != null) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(Color.Red)
                    .then(modifier),
        ) {
            STextTitle("Access Control Interception")
        }
    }
}
