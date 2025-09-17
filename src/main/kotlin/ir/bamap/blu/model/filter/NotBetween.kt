package ir.bamap.blu.model.filter

import ir.bamap.blu.model.util.FilterUtil

open class NotBetween(
    propertyName: String = "",
    lowerBoundary: Any = 0,
    upperBoundary: Any = 0
) : Between(propertyName, lowerBoundary, upperBoundary) {
    override fun isMatched(value: Any): Boolean {
        return !super.isMatched(value)
    }

    override fun toString(): String {
        return "\"$propertyName\" NOT BETWEEN $lowerBoundary AND $upperBoundary"
    }

    override fun clone(): NotBetween = NotBetween(propertyName, lowerBoundary, upperBoundary)

    override fun getNamedParameterSql(): NamedParameterSql {
        val randomStr = FilterUtil.randomString(4)
        val lowerParameterName = propertyName + "_lower_" + randomStr
        val upperParameterName = propertyName + "_upper_" + randomStr
        return NamedParameterSql(
            "\"$propertyName\" NOT BETWEEN :$lowerParameterName AND :$upperParameterName",
            mapOf(
                lowerParameterName to FilterUtil.getValueSqlClause(lowerBoundary),
                upperParameterName to FilterUtil.getValueSqlClause(upperBoundary)
            )
        )
    }
}
