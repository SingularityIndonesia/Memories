package development

import androidx.compose.runtime.Composable
import core.tool.SimpleActivity
import core.ui.SingularityApp
import development.example.presentation.pane.ExamplePane

class DevelopmentPreviewActivity : SimpleActivity() {
    @Composable
    override fun App() {
        SingularityApp {
            ExamplePane() // replace this with your module
        }
    }
}
