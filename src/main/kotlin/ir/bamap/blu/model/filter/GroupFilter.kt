package ir.bamap.blu.model.filter

abstract class GroupFilter(
    val filters: Filters = Filters(),
) : FilterModel() {

    abstract override fun clone(): GroupFilter

    protected fun getSql(separator: String): String {

        if (filters.isEmpty())
            return ""

        val sqls = filters.map { it.toString() }
            .filter { it.isNotEmpty() }

        if (sqls.isEmpty())
            return ""

        if (sqls.size == 1)
            return sqls[0]

        return sqls.joinToString(separator, "(", ")")
    }

    protected fun getNamedParameterSql(separator: String): NamedParameterSql {
        val namedParameters = filters.map { it.getNamedParameterSql() }
            .filter { it.sql.isNotEmpty()}

        if(namedParameters.isEmpty())
            return NamedParameterSql()

        val finalSql = namedParameters.joinToString(" $separator ", "(", ")") { it.sql }

        val finalParameters = mutableMapOf<String, Any?>()
        namedParameters.forEach {
            finalParameters += it.parameters
        }

        return NamedParameterSql(finalSql, finalParameters)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GroupFilter) return false

        if (other::class.java != this::class.java)
            return false

        return filters == other.filters
    }

    override fun hashCode(): Int {
        return filters.hashCode() + this::class.java.hashCode()
    }


}
