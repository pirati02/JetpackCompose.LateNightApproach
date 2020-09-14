package ge.baqar.myapplication.ui.scenes.arch

sealed class ReactiveResult<out L, out R>
data class FailedResult<out L>(val value: L) : ReactiveResult<L, Nothing>()
data class SucceedResult<out R>(val value: R) : ReactiveResult<Nothing, R>()

inline fun <L, R, T> ReactiveResult<L, R>.fold(onLeft: (L) -> T, onRight: (R) -> T) = when (this) {
    is FailedResult -> onLeft(value)
    is SucceedResult -> onRight(value)
}

val <T> T.asError: FailedResult<T> get() = FailedResult(this)

val <T> T.asSuccess: SucceedResult<T> get() = SucceedResult(this)