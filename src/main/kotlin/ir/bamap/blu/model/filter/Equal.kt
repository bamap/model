package ir.bamap.blu.model.filter

import ir.bamap.blu.model.util.FilterUtil
import ir.bamap.blu.model.util.Util


open class Equal(propertyName: String = "", literal: Any = "") : ComparativeOperatorFilter(propertyName, literal) {

    override fun toString(): String = "\"$propertyName\" = ${FilterUtil.getValueSqlClause(literal)}"

    override fun clone(literal: Any, propertyName: String): Equal = Equal(propertyName, literal)

    override fun getNamedParameterSql(): NamedParameterSql {
        val parameterName = propertyName + "_" + Util.nextString(4)
        return NamedParameterSql(
            "\"$propertyName\" = :$parameterName",
            mapOf(parameterName to literal)
        )
    }
}
