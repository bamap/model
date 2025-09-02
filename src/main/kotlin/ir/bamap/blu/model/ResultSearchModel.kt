package ir.bamap.blu.model

import kotlin.math.max
import kotlin.math.min

open class ResultSearchModel<T>(
    open val records: List<T> = listOf(),
    open val total: Long = records.size.toLong()
) {

    @Deprecated("Use mapRecords instead", replaceWith = ReplaceWith("mapRecords(transform)"))
    fun <R> map(transform: (T) -> R): ResultSearchModel<R> {
        val mappedResults = records.map(transform)

        return ResultSearchModel(mappedResults, total)
    }

    fun <R> mapRecords(transform: (T) -> R): ResultSearchModel<R> {
        val mappedResults = records.map(transform)

        return ResultSearchModel(mappedResults, total)
    }

    inline fun <reified R : ResultSearchModel<T>> map(): R {
        return R::class.constructors.first().call(records, total)
    }

    open fun getPage(page: Int = 1, limit: Int = 50, start: Int = 0): ResultSearchModel<T> {

        val pageRecords = mutableListOf<T>()
        val result = ResultSearchModel(pageRecords, this.total)

        val startIndex = max(start, start + (page - 1) * limit)
        if (startIndex > records.size)
            return result

        val lastIndex = if (limit <= 0)
            records.size
        else
            min(records.size, start + page * limit)

        pageRecords.addAll(records.subList(startIndex, lastIndex))

        return result
    }

    open fun getPage(page: PageSearchModel): ResultSearchModel<T> = getPage(page.page, page.limit)
}
