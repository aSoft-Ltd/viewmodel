package tz.co.asoft

internal val <T : Any> T.toDetailedString: String
    get() = when {
        "$this" == "[object Object]" -> this::class.simpleName ?: "Unknown"
        "$this".contains("${'$'}State${'$'}") -> this::class.simpleName ?: "Unknown"
        "$this".contains("${'$'}Intent${'$'}") -> this::class.simpleName ?: "Unknown"
        else -> toString()
    }