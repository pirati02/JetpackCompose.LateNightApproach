package ge.baqar.myapplication.ui.scenes.arch

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch


@OptIn(ExperimentalCoroutinesApi::class)
abstract class ReactiveViewModel<Action, State>(initialState: State) : ViewModel() {

    private val intentChannel = Channel<Action>(Channel.UNLIMITED)
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State>
        get() = _state

    init {
        GlobalScope.launch(Dispatchers.Default) {
            handleIntents()
        }
    }

    fun newIntent(action: Action) {
        intentChannel.offer(action)
    }

    private suspend fun handleIntents() {
        intentChannel.consumeAsFlow()
            .collect { _state.value = handleIntent(it) }
    }

    protected abstract fun handleIntent(action: Action): State
}