package ir.bamap.blu.model

open class OrderModel(
    val propertyName: String = "",
    val direction: Direction = Direction.ASC
) {

    enum class Direction {
        ASC, DESC
    }

    fun clone(): OrderModel = OrderModel(this.propertyName, this.direction)

    open fun getSql(): String = "$propertyName $direction"

    override fun toString(): String = getSql()
}
