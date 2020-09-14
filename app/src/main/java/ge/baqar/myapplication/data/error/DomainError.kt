package ge.baqar.myapplication.data.error

interface DomainError {
    val message: String?
    val exception: Exception?
}