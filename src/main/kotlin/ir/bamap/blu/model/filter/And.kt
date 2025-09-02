package ir.bamap.blu.model.filter

open class And(
    filters: Filters = Filters()
) : GroupFilter(filters), Cloneable {

    constructor(vararg filters: FilterModel) : this(Filters(*filters))

    override fun toString(): String = super.getSql(" AND ")

    override fun clone(): And = And(filters.clone())

    override fun getNamedParameterSql(): NamedParameterSql = super.getNamedParameterSql("AND")
}
