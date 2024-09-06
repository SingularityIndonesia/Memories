package dashboard.pane.memories

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.ui.designsystem.component.STextTitle

@Composable
fun MemoriesPane(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        STextTitle("Memories Pane")
    }
}
