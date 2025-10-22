package ir.bamap.blu.model.filter

import ir.bamap.blu.model.util.FilterUtil

open class In(
    propertyName: String = "",
    val literal: Collection<Any?> = emptyList()
) : OperatorFilter(propertyName) {

    override fun toString(): String {

        val values = literal.joinToString(",") {
            if (it == null)
                "null"
            else
                FilterUtil.getValueSqlClause(it)
        }
        return "\"$propertyName\" IN($values)"
    }

    override fun clone(): In = In(propertyName, literal)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is In) return false
        if (!super.equals(other)) return false

        if (literal != other.literal) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + literal.hashCode()
        return result
    }

    override fun getNamedParameterSql(): NamedParameterSql {
        return getNamedParameterByOperator("IN")
    }

    /**
     * @param sqlOperator It can be only IN OR NOT IN
     */
    protected fun getNamedParameterByOperator(sqlOperator: String): NamedParameterSql {
        val values = literal.map {
            if (it == null)
                null
            else
                FilterUtil.getValueSqlClause(it)
        }

        val parameters = values.filterNotNull().associateBy { "in_${FilterUtil.randomString(4)}" }

        val inKeys: MutableList<String?> = parameters.keys.map { ":$it" }.toMutableList()
        if (values.contains(null))
            inKeys.add(null)

        val sql = "\"$propertyName\" $sqlOperator (${inKeys.joinToString(",")})"
        return NamedParameterSql(sql, parameters)
    }
}
