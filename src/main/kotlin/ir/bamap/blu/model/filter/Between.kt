package ir.bamap.blu.model.filter

import ir.bamap.blu.model.util.FilterUtil
import ir.bamap.blu.model.util.Util

open class Between(
    propertyName: String = "",
    open val lowerBoundary: Any = 0,
    open val upperBoundary: Any = 0
) : OperatorFilter(propertyName) {

    override fun clone(): Between = Between(propertyName, lowerBoundary, upperBoundary)

    open fun isMatched(value: Any): Boolean {

        if (lowerBoundary !is Comparable<*>)
            return false

        value as Comparable<*>
        val finalLowerBoundary = lowerBoundary as Comparable<*>
        val finalUpperBoundary = upperBoundary as Comparable<*>

        if (compareValues(finalLowerBoundary, value) > 0)
            return false

        if (compareValues(value, finalUpperBoundary) > 0)
            return false

        return true
    }

    override fun toString(): String {
        return "\"$propertyName\" BETWEEN $lowerBoundary AND $upperBoundary"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other !is Between) return false

        if (this::class.java != other::class.java)
            return false

        if (propertyName != other.propertyName) return false
        if (lowerBoundary != other.lowerBoundary) return false
        if (upperBoundary != other.upperBoundary) return false

        return true
    }

    override fun hashCode(): Int {
        var result = lowerBoundary.hashCode()
        result = 31 * result + upperBoundary.hashCode()
        return result
    }

    override fun getNamedParameterSql(): NamedParameterSql {
        val randomStr = Util.nextString(4)
        val lowerParameterName = propertyName + "_lower_" + randomStr
        val upperParameterName = propertyName + "_upper_" + randomStr
        return NamedParameterSql(
            "\"$propertyName\" BETWEEN :$lowerParameterName AND :$upperParameterName",
            mapOf(
                lowerParameterName to FilterUtil.getValueSqlClause(lowerBoundary),
                upperParameterName to FilterUtil.getValueSqlClause(upperBoundary)
            )
        )
    }
}
