package ir.bamap.blu.model

import ir.bamap.blu.model.filter.FilterModel
import ir.bamap.blu.model.filter.Filters

open class SearchModel(
    page: Int = 0,
    limit: Int = 20,

    open val filters: Filters = Filters(),

    open val orders: Orders = Orders()
) : PageSearchModel(page, limit) {

    fun filter(filter: FilterModel?): SearchModel {
        if (filter != null)
            this.filters.add(filter)
        return this
    }

    fun order(order: OrderModel?): SearchModel {
        if (order != null)
            this.orders.add(order)
        return this
    }

    override fun clone(): SearchModel {
        return SearchModel(this.page, this.limit, this.filters.clone(), this.orders.clone())
    }

    open fun removeUnknownProperties(knownProperties: Collection<String>): SearchModel {
        this.filters.removeUnknownProperties(knownProperties)
        this.orders.removeUnknownProperties(knownProperties)
        return this
    }

    open fun removeByPropertyNames(properties: Collection<String>): SearchModel {
        this.filters.removeByPropertyNames(properties)
        this.orders.removeByPropertyNames(properties)
        return this
    }

    open fun removeByPropertyName(property: String): SearchModel = removeByPropertyNames(setOf(property))
}
