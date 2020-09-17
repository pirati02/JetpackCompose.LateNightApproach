package ge.baqar.myapplication.ui.scenes.dashboard.state

import ge.baqar.myapplication.ui.scenes.auth.state.AppState


//Actions
sealed class DashboardAction{
    object SetInitialStateAction : DashboardAction()
    data class SetUserInfo(var userInfo: AppState.UserInfo?) : DashboardAction()
}

//State
data class DashboardState(val userInfo: AppState.UserInfo?, ) {
    companion object {
        val DEFAULT = DashboardState(
            userInfo = null
        )
    }
}