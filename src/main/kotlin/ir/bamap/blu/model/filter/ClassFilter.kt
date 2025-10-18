package ir.bamap.blu.model.filter

class ClassFilter(
    val cls: Class<*>
): FilterModel() {
    override fun clone(): FilterModel = ClassFilter(cls)

    override fun getNamedParameterSql(): NamedParameterSql {
        return NamedParameterSql()
    }
}