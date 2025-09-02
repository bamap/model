package ir.bamap.blu.model

import java.util.ArrayList

open class Orders() : java.util.ArrayList<OrderModel>() {

    constructor(vararg order: OrderModel) : this() {
        this.addAll(order)
    }

    constructor(vararg properties: String) : this() {
        properties.forEach { this.add(OrderModel(it, OrderModel.Direction.ASC)) }
    }

    open fun removeUnknownProperties(knownProperties: Collection<String>) {
        this.removeIf { order -> !knownProperties.contains(order.propertyName) }
    }

    open fun removeByPropertyNames(properties: Collection<String>) =
        this.removeIf { properties.contains(it.propertyName) }

    open fun removeByPropertyName(property: String) = removeByPropertyNames(setOf(property))

    override fun clone(): Orders {
        val cloned = Orders()
        this.forEach { cloned.add(it.clone()) }

        return cloned
    }

    fun getSql(): String {
        if (this.isEmpty())
            return ""

        return this.joinToString(", ", "ORDER BY ")
    }

    override fun toString(): String = getSql()
}
