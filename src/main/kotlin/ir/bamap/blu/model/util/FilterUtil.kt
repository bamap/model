package ir.bamap.blu.model.util

import ir.bamap.blu.model.filter.FilterModel
import ir.bamap.blu.model.filter.GroupFilter
import ir.bamap.blu.model.filter.NotFilter
import java.security.InvalidParameterException
import java.util.*

class FilterUtil {
    companion object {
        const val CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"

        @JvmStatic
        fun randomString(length: Int): String {
            return List(length) {
                CHARACTERS.random()
            }.joinToString("")
        }

        @JvmStatic
        fun getValueSqlClause(value: Any): String {
            if (literalIsField(value))
                return "\"${getField(value)}\""

            if (value is String)
                return "'$value'"

            return "$value"
        }

        @JvmStatic
        fun flat(filters: MutableCollection<FilterModel>) : Collection<FilterModel> {
            filters.removeIf { removeEmptyHierarchy(it) }

            return filters
        }

        private fun getField(literal: Any): String {
            val regex = "\\{\\{.*\\}\\}".toRegex()

            val match = regex.find(literal.toString()) ?: throw RuntimeException("")
            val value = match.value
            return value.substring(2, value.length - 2)
        }

        private fun literalIsField(literal: Any): Boolean {
            if (literal !is String)
                return false

            val regex = "\\{\\{.*\\}\\}".toRegex()
            return literal.matches(regex)
        }

        private fun removeEmptyHierarchy(filter: FilterModel): Boolean {
            if(filter is GroupFilter) {
                filter.filters.flatten()
                return filter.filters.isEmpty()
            }

            if(filter is NotFilter)
                return removeEmptyHierarchy(filter.filter)

            // is OperatorFilter
            return false
        }
    }
}
