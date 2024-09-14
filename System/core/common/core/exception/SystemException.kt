package core.exception

sealed class SystemException(
    throwable: Throwable?,
) : Exception(throwable)

data class AuthenticationException(
    val throwable: Throwable?,
) : SystemException(throwable)

data class IdentificationException(
    val throwable: Throwable?,
) : SystemException(throwable)

data class IllegalArgumentException(
    val throwable: Throwable?,
) : SystemException(throwable)

data class NullPointerException(
    val throwable: Throwable?,
) : SystemException(throwable)

data class InterruptedException(
    val throwable: Throwable?,
) : SystemException(throwable)

data class AccessControlException(
    val throwable: Throwable?,
) : SystemException(throwable)

data class IOException(
    val throwable: Throwable?,
) : SystemException(throwable)

data class CancellationException(
    val throwable: Throwable?,
) : SystemException(throwable)

data class UnknownException(
    val throwable: Throwable?,
) : SystemException(throwable)
