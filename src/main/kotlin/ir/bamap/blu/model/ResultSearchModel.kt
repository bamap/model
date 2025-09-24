package ir.bamap.blu.model

import kotlin.math.min

open class ResultSearchModel<T>(
    open val records: List<T> = listOf(),
    open val total: Long = records.size.toLong()
) {

    fun <R> mapRecords(transform: (T) -> R): ResultSearchModel<R> {
        val mappedResults = records.map(transform)

        return ResultSearchModel(mappedResults, total)
    }

    /**
     * Maps the current ResultSearchModel to a target type R which must extend ResultSearchModel<T>.
     * 
     * The target class R must have a primary constructor with exactly two parameters in the following order:
     * 1. records: List<T> (with default value if needed)
     * 2. total: Long (with default value if needed)
     * 
     * This method uses reflection to instantiate the target type, which requires the kotlin-reflect dependency.
     * 
     * @return A new instance of type R containing the same records and total count
     * @throws IllegalArgumentException if the target class doesn't have a compatible constructor
     */
    inline fun <reified R : ResultSearchModel<T>> map(): R {
        return R::class.constructors.first().call(records, total) // It need kotlin-reflect dependency
    }

    /**
     * Page start from 0 like Spring Data JPA
     */
    open fun getPage(page: Int = 0, limit: Int = 50, start: Int = 0): ResultSearchModel<T> {

        if (limit <= 0)
            return this.mapRecords { it }

        val startIndex = start + page * limit
        if (startIndex > records.size)
            return ResultSearchModel(emptyList(), total)

        val lastIndex = min(records.size, start + (page + 1) * limit)

        return ResultSearchModel(records.subList(startIndex, lastIndex), this.total)
    }

    open fun getPage(page: PageSearchModel): ResultSearchModel<T> = getPage(page.page, page.limit)
}
