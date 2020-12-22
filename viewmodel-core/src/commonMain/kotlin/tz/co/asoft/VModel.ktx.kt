package tz.co.asoft

internal val <T : Any> T.toDetailedString: String
    get() = when {
        "$this" == "[object Object]" -> this::class.simpleName ?: "Unknown"
        "$this".contains("${'$'}State${'$'}") -> this::class.simpleName ?: "Unknown"
        "$this".contains("${'$'}Intent${'$'}") -> this::class.simpleName ?: "Unknown"
        else -> toString()
    }

internal fun VModel<*, *>.log(msg: String) = when {
    msg.contains("fail", ignoreCase = true) -> logger.failure(msg)
    msg.contains("error", ignoreCase = true) -> logger.error(msg)
    else -> logger.info(msg)
}