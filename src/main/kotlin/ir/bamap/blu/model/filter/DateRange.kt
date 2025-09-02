package ir.bamap.blu.model.filter

import java.util.*

open class DateRange(
    propertyName: String = "",
    override val lowerBoundary: Date = Date(),
    override val upperBoundary: Date = Date()
): Between(propertyName, lowerBoundary, upperBoundary) {

    override fun clone(): DateRange = DateRange(propertyName, lowerBoundary, upperBoundary)
}