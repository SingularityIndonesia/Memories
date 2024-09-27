package authentication.pane.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import authentication.AuthenticationRepository
import core.adt.SystemResult
import core.adt.onSuccess
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import profile.ProfileRepository

class LoginViewModel(
    private val authenticationModel: AuthenticationRepository = AuthenticationRepository(),
    private val profileModel: ProfileRepository = ProfileRepository(),
) : ViewModel(),
    ContainerHost<LoginPaneState, LoginPaneSideEffect> {
    override val container: Container<LoginPaneState, LoginPaneSideEffect> =
        viewModelScope.container(LoginPaneState())

    fun login() =
        intent {
            reduce { state.copy(showLoading = true) }
            val result =
                authenticationModel
                    .login()
                    .onSuccess { data ->
                        viewModelScope.launch {
                            authenticationModel.saveToken(data.token)
                            // profileModel.saveUserBasicInfo(data.user)
                        }
                    }

            val newState =
                when (result) {
                    is SystemResult.Success -> {
                        state.copy(
                            showLoading = false,
                        )
                    }

                    is SystemResult.Error ->
                        state.copy(
                            showLoading = false,
                            showLoginErrorSnackBar = true,
                            loginErrorSnackBarMessage = "Login Failed: ${result.e.cause?.message}",
                        )
                }

            reduce { newState }
        }

    fun closeLoginBottomSheet() =
        intent {
            reduce { state.copy(showBottomSheet = false) }
        }

    fun dismissLoginErrorSnackBar() =
        intent {
            reduce { state.copy(showLoginErrorSnackBar = false) }
        }
}
