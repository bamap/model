package ir.bamap.blu.model.filter

import ir.bamap.blu.model.util.FilterUtil
import ir.bamap.blu.model.util.Util

open class Like(
    propertyName: String = "",
    override val literal: String = ""
) : ComparativeOperatorFilter(propertyName, literal) {

    override fun toString(): String = "\"$propertyName\" LIKE ${FilterUtil.getValueSqlClause(literal)}"

    override fun clone(literal: Any, propertyName: String): Like = Like(propertyName, literal.toString())

    override fun getNamedParameterSql(): NamedParameterSql {
        val parameterName = propertyName + "_" + Util.nextString(4)
        return NamedParameterSql(
            "\"$propertyName\" LIKE :$parameterName",
            mapOf(parameterName to literal)
        )
    }
}
