package ge.baqar.myapplication.ui.scenes.auth

import ge.baqar.myapplication.ui.scenes.arch.ReactiveViewModel
import ge.baqar.myapplication.ui.scenes.auth.state.AuthViewAction
import ge.baqar.myapplication.ui.scenes.auth.state.AuthViewState

class AuthViewModel : ReactiveViewModel<AuthViewAction, AuthViewState>(AuthViewState.DEFAULT) {
    override fun handleIntent(action: AuthViewAction): AuthViewState {
        return AuthViewState.DEFAULT
    }
}