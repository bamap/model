package ir.bamap.blu.model.filter

import ir.bamap.blu.model.util.FilterUtil
import ir.bamap.blu.model.util.Util

open class NotLike(
    propertyName: String = "",
    override val literal: String = ""
) : ComparativeOperatorFilter(propertyName, literal) {

    override fun toString(): String = "\"$propertyName\" NOT LIKE ${FilterUtil.getValueSqlClause(literal)}"

    override fun clone(literal: Any, propertyName: String): NotLike = NotLike(propertyName, literal.toString())

    override fun getNamedParameterSql(): NamedParameterSql {
        val parameterName = propertyName + "_" + Util.nextString(4)
        return NamedParameterSql(
            "\"$propertyName\" NOT LIKE :$parameterName",
            mapOf(parameterName to literal)
        )
    }
}
