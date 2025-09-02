package ir.bamap.blu.model.filter

import ir.bamap.blu.exception.NotSupportedException
import ir.bamap.blu.model.util.FilterUtil


open class Filters() : ArrayList<FilterModel>(), Cloneable {

    constructor(vararg filter: FilterModel) : this() {
        this.addAll(filter)
    }

    open fun removeUnknownProperties(knownProperties: Collection<String>): Filters {
        this.removeIf { removeUnknownPropertiesHierarchy(it, knownProperties) }

        return this
    }

    open fun removeByPropertyNames(properties: Collection<String>): Filters {
        this.removeIf { removeByPropertyNamesHierarchy(it, properties) }

        return this
    }

    open fun removeByPropertyName(property: String): Filters = removeByPropertyNames(setOf(property))

    open fun flatten(): Filters {
        FilterUtil.flat(this)
        return this
    }

    open fun getPropertyNames(): Set<String> {
        val properties = mutableSetOf<String>()

        this.forEach { filter ->
            val childProperties = when (filter) {
                is GroupFilter -> filter.filters.getPropertyNames()
                is OperatorFilter -> mutableSetOf(filter.propertyName)
                else -> throw NotSupportedException(filter::class.java, "LIB-MODEL_FILTERS_GET_PROPERTY_NAME")
            }

            properties.addAll(childProperties)
        }
        return properties
    }

    fun getNamedParameterSql(joinType: Class<out GroupFilter> = And::class.java): NamedParameterSql {
        val filter = when (joinType) {
            And::class.java -> And()
            Or::class.java -> Or()
            else -> throw NotSupportedException(joinType, "LIB-MODEL_FILTERS_GET_NAMED_PARAMETER_SQL")
        }
        filter.filters.addAll(this)

        return filter.getNamedParameterSql()
    }

    protected open fun removeByPropertyNamesHierarchy(filter: FilterModel, properties: Collection<String>): Boolean {
        if (filter is GroupFilter) {
            filter.filters.removeByPropertyNames(properties)
            return filter.filters.isEmpty()
        }

        if (filter is NotFilter) {
            return removeByPropertyNamesHierarchy(filter.filter, properties)
        }

        return filter is OperatorFilter && properties.contains(filter.propertyName)
    }

    protected open fun removeUnknownPropertiesHierarchy(
        filter: FilterModel,
        knownProperties: Collection<String>
    ): Boolean {
        if (filter is GroupFilter) {
            filter.filters.removeIf { removeUnknownPropertiesHierarchy(it, knownProperties) }
            return false
        }

        if (filter is NotFilter)
            return removeUnknownPropertiesHierarchy(filter.filter, knownProperties)

        if (filter !is OperatorFilter)
            return true

        return !knownProperties.contains(filter.propertyName)
    }

    override fun clone(): Filters {
        val filterList = Filters()
        val filters = this.map { it.clone() }
        filterList.addAll(filters)
        return filterList
    }

    override fun toString(): String = this.toTypedArray().contentToString()

    override fun hashCode(): Int {
        return this.sumOf { it.hashCode() }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Filters) return false
        if (!super.equals(other)) return false
        return true
    }
}
