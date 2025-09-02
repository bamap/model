package ir.bamap.blu.model.filter

abstract class ComparativeOperatorFilter(
    propertyName: String = "",
    open val literal: Any = 0
) : OperatorFilter(propertyName) {

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true

        if (other !is ComparativeOperatorFilter)
            return false

        if (other::class.java != this::class.java)
            return false

        return propertyName == other.propertyName && literal == other.literal
    }

    override fun hashCode(): Int {
        return literal.hashCode() * 13 +
                propertyName.hashCode() * 6 +
                this::class.java.hashCode()
    }

    override fun clone(): FilterModel = clone(literal)

    abstract fun clone(literal: Any, propertyName: String = this.propertyName): ComparativeOperatorFilter
    
}
