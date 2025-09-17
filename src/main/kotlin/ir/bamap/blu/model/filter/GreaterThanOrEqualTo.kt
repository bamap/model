package ir.bamap.blu.model.filter

import ir.bamap.blu.model.util.FilterUtil


open class GreaterThanOrEqualTo(
    propertyName: String = "",
    literal: Any = 0
) : ComparativeOperatorFilter(propertyName, literal) {

    override fun toString(): String = "\"$propertyName\" >= ${FilterUtil.getValueSqlClause(literal)}"

    override fun clone(literal: Any, propertyName: String): GreaterThanOrEqualTo = GreaterThanOrEqualTo(propertyName, literal)

    override fun getNamedParameterSql(): NamedParameterSql {
        val parameterName = propertyName + "_" + FilterUtil.randomString(4)
        return NamedParameterSql(
            "\"$propertyName\" >= :$parameterName",
            mapOf(parameterName to literal)
        )
    }
}
