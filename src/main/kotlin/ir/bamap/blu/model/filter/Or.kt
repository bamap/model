package ir.bamap.blu.model.filter

open class Or(
    filters: Filters = Filters()
) : GroupFilter(filters) {

    constructor(vararg filters: FilterModel = arrayOf()) : this(Filters(*filters))

    override fun toString(): String = super.getSql(" OR ")

    override fun clone(): Or = Or(filters.clone())

    override fun getNamedParameterSql(): NamedParameterSql = super.getNamedParameterSql("OR")
}
