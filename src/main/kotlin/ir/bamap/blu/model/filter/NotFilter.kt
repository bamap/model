package ir.bamap.blu.model.filter

class NotFilter(val filter: FilterModel = And()): FilterModel() {

    override fun clone(): FilterModel = NotFilter(filter.clone())

    override fun getNamedParameterSql(): NamedParameterSql {
        val filterNamedSql = filter.getNamedParameterSql()
        return NamedParameterSql("NOT(${filterNamedSql.sql})", filterNamedSql.parameters)
    }

    override fun toString(): String {
        return "Not(${filter})"
    }
}
