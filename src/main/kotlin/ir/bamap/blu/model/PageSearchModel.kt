package ir.bamap.blu.model

open class PageSearchModel(
    open val page: Int = 0,
    open val limit: Int = 20,
) {

    open fun clone(): PageSearchModel {
        return PageSearchModel(this.page, this.limit)
    }

    open fun getFirstResult(): Int {
        if (limit <= 0) return 0

        return page * limit
    }
}
