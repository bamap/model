package ir.bamap.blu.model

import ir.bamap.blu.model.filter.Between
import ir.bamap.blu.model.filter.FilterModel
import ir.bamap.blu.model.filter.GreaterThanOrEqualTo
import ir.bamap.blu.model.filter.LessThanOrEqualTo

class RangeModel<Type>(
    val from: Type? = null,
    val to: Type? = null
) {

    fun getFilterModel(propertyName: String): FilterModel? {
        if (from != null && to != null)
            return Between(propertyName, from, to)

        if (from != null)
            return GreaterThanOrEqualTo(propertyName, from)

        if (to != null)
            return LessThanOrEqualTo(propertyName, to)

        return null
    }
}