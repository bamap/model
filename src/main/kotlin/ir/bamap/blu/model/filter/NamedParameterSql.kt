package ir.bamap.blu.model.filter

import java.io.Serializable

class NamedParameterSql(
    val sql: String = "",
    val parameters: Map<String, Any?> = emptyMap()
): Serializable {
}
