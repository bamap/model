package ir.bamap.blu.model.filter

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.io.Serializable

/**
 * @author m.malvandi
 , */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "operator", include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes(
    JsonSubTypes.Type(value = And::class, name = "and"),
    JsonSubTypes.Type(value = Between::class, name = "between"),
    JsonSubTypes.Type(value = DateRange::class, name = "dateRange"),
    JsonSubTypes.Type(value = Equal::class, name = "equal"),
    JsonSubTypes.Type(value = GreaterThan::class, name = "gt"),
    JsonSubTypes.Type(value = GreaterThan::class, name = "greaterThan"),
    JsonSubTypes.Type(value = GreaterThanOrEqualTo::class, name = "gte"),
    JsonSubTypes.Type(value = GreaterThanOrEqualTo::class, name = "greaterThanOrEqualTo"),
    JsonSubTypes.Type(value = IsNull::class, name = "isNull"),
    JsonSubTypes.Type(value = IsNotNull::class, name = "isNotNull"),
    JsonSubTypes.Type(value = LessThan::class, name = "lt"),
    JsonSubTypes.Type(value = LessThan::class, name = "lessThan"),
    JsonSubTypes.Type(value = LessThanOrEqualTo::class, name = "lte"),
    JsonSubTypes.Type(value = LessThanOrEqualTo::class, name = "lessThanOrEqualTo"),
    JsonSubTypes.Type(value = Like::class, name = "like"),
    JsonSubTypes.Type(value = NotFilter::class, name = "not"),
    JsonSubTypes.Type(value = NotBetween::class, name = "notBetween"),
    JsonSubTypes.Type(value = NotEqual::class, name = "notEqual"),
    JsonSubTypes.Type(value = NotLike::class, name = "notLike"),
    JsonSubTypes.Type(value = NotAnd::class, name = "notAnd"),
    JsonSubTypes.Type(value = NotOr::class, name = "notOr"),
    JsonSubTypes.Type(value = Or::class, name = "or")
)
abstract class FilterModel : Serializable, Cloneable {

    public abstract override fun clone(): FilterModel

    @JsonIgnore
    abstract fun getNamedParameterSql(): NamedParameterSql
}
