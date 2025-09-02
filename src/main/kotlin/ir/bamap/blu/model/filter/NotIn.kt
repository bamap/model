package ir.bamap.blu.model.filter

import ir.bamap.blu.model.util.FilterUtil

open class NotIn(
    propertyName: String = "",
    literal: List<Any?> = emptyList()
) : ir.bamap.blu.model.filter.In(propertyName, literal) {

    override fun toString(): String {

        val values = literal.map {
            if (it == null)
                null
            else
                FilterUtil.getValueSqlClause(it)
        }.joinToString(",")
        return "\"$propertyName\" NOT IN($values)"
    }

    override fun clone(): ir.bamap.blu.model.filter.NotIn = ir.bamap.blu.model.filter.NotIn(propertyName, literal)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ir.bamap.blu.model.filter.NotIn) return false
        if (!super.equals(other)) return false

        if (literal != other.literal) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + literal.hashCode()
        return result
    }

    override fun getNamedParameterSql(): ir.bamap.blu.model.filter.NamedParameterSql {
        return getNamedParameterByOperator("NOT IN")
    }
}
