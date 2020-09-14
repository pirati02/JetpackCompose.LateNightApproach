package ge.baqar.myapplication.data.error

data class ConnectionError(
    override val message: String,
    override val exception: Exception
) : DomainError