package ir.bamap.blu.model.filter

import ir.bamap.blu.model.util.FilterUtil

open class NotIn(
    propertyName: String = "",
    literal: Collection<Any?> = emptyList()
) : In(propertyName, literal) {

    override fun toString(): String {

        val values = literal.joinToString(",") {
            if (it == null)
                "null"
            else
                FilterUtil.getValueSqlClause(it)
        }
        return "\"$propertyName\" NOT IN($values)"
    }

    override fun clone(): NotIn = NotIn(propertyName, literal)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NotIn) return false
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
        return getNamedParameterByOperator("NOT IN")
    }
}
