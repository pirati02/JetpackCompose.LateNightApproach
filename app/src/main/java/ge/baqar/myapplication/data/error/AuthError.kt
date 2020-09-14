package ge.baqar.myapplication.data.error

import ge.baqar.myapplication.data.error.DomainError

data class AuthError(
    override val message: String?,
    override val exception: Exception?
) : DomainError