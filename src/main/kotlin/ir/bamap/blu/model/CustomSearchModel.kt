package ir.bamap.blu.model

abstract class CustomSearchModel(
    page: Int = 0,
    limit: Int = 20,
): PageSearchModel(page, limit) {

    abstract fun toSearchModel(): SearchModel
}