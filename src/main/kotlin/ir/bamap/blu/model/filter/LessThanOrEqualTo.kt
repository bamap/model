package ir.bamap.blu.model.filter

import ir.bamap.blu.model.util.FilterUtil
import ir.bamap.blu.model.util.Util

open class LessThanOrEqualTo(
    propertyName: String = "",
    literal: Any = 0
) : ComparativeOperatorFilter(propertyName, literal) {

    override fun toString(): String = "\"$propertyName\" <= ${FilterUtil.getValueSqlClause(literal)}"

    override fun clone(literal: Any, propertyName: String): LessThanOrEqualTo = LessThanOrEqualTo(propertyName, literal)

    override fun getNamedParameterSql(): NamedParameterSql {
        val parameterName = propertyName + "_" + Util.nextString(4)
        return NamedParameterSql(
            "\"$propertyName\" <= :$parameterName",
            mapOf(parameterName to literal)
        )
    }
}
