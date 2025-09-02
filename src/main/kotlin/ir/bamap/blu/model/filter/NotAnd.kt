package ir.bamap.blu.model.filter

open class NotAnd(
    filters: Filters = Filters()
) : And(filters), Cloneable {

    constructor(vararg filters: FilterModel) : this(Filters(*filters))

    override fun toString(): String = "NOT(${super.toString()})"

    override fun clone(): NotAnd = NotAnd(filters.clone())

    override fun getNamedParameterSql(): NamedParameterSql {
        val andNamedParameterSql = super.getNamedParameterSql()
        if(andNamedParameterSql.sql.isEmpty())
            return andNamedParameterSql

        return NamedParameterSql("NOT(${andNamedParameterSql.sql})", andNamedParameterSql.parameters)
    }
}
