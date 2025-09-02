package ir.bamap.blu.model.filter

open class IsNull(propertyName: String = "") : OperatorFilter(propertyName) {

    override fun toString(): String = "\"$propertyName\" is NULL"

    override fun clone(): IsNull = IsNull(propertyName)

    override fun getNamedParameterSql(): NamedParameterSql {
        return NamedParameterSql(
            "\"$propertyName\" IS NULL"
        )
    }
}
