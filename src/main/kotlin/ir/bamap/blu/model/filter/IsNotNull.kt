package ir.bamap.blu.model.filter

open class IsNotNull(propertyName: String = "") : OperatorFilter(propertyName) {

    override fun toString(): String = "\"$propertyName\" is not NULL"

    override fun clone(): IsNotNull = IsNotNull(propertyName)

    override fun getNamedParameterSql(): NamedParameterSql {
        return NamedParameterSql("\"$propertyName\" IS NOT NULL")
    }
}
