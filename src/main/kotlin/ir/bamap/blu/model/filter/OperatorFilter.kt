package ir.bamap.blu.model.filter

abstract class OperatorFilter(val propertyName: String = "") : FilterModel() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OperatorFilter) return false

        if (this::class.java != other::class.java)
            return false

        if (propertyName != other.propertyName) return false

        return true
    }

    override fun hashCode(): Int {
        return propertyName.hashCode() * 11 + this::class.java.hashCode() * 3
    }


}
