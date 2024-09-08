package development.example.presentation.pane

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import core.ui.SingularityApp
import core.ui.SingularityScope
import core.ui.designsystem.molecule.Expand
import core.ui.designsystem.molecule.SPrimaryButton
import core.ui.designsystem.molecule.STextLabel
import development.example.presentation.organism.ExampleOrganism
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

context(SingularityScope)
@Composable
fun ExamplePane(viewModel: ExamplePaneViewModel = viewModel()) {
    val context = LocalContext.current // example purpose only, do not depend on OS Context
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is ShowToast -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_LONG).show()
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        ExampleOrganism()

        // display entities
        if (state.labelItems.isNotEmpty()) {
            EntitiesLabelList(list = state.labelItems)
        }

        if (state.showLoading) {
            CircularProgressIndicator()
        }

        Expand()
        SPrimaryButton(
            onClick = { viewModel.showToast() },
            labelText = "Show Toast",
        )
    }
}

@Composable
fun EntitiesLabelList(list: List<String>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        list.map {
            STextLabel(text = it)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Default() {
    SingularityApp {
        ExamplePane()
    }
}
