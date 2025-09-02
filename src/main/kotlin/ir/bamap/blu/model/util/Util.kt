package ir.bamap.blu.model.util

import java.util.*

class Util {
    companion object {
        const val BIG_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        const val SMALL_LETTERS = "abcdefghijklmnopqrstuvwxyz"
        const val NUMBERS = "0123456789"

        @JvmStatic
        fun nextString(length: Int, characters: String = "$BIG_LETTERS$SMALL_LETTERS$NUMBERS"): String {
            return List(length) {
                characters.random()
            }.joinToString("")
        }

        @JvmStatic
        fun toNumberOrNull(value: Any?): Number? {
            if (value == null)
                return null

            if(value is Number)
                return value

            val stringValue = value.toString();
            val int = stringValue.toIntOrNull()
            if (int != null)
                return int

            val long = stringValue.toLongOrNull()
            if (long != null)
                return long

            val float = stringValue.toFloatOrNull()
            if (float != null && float.isFinite())
                return float

            return stringValue.toDoubleOrNull()
        }

        /**
         * @return get Boolean value type if [value] is similar to TRUE, True and etc.
         */
        @JvmStatic
        fun toBooleanOrNull(value: Any?): Boolean? {
            if (value == null)
                return null

            if(value is Boolean)
                return value

            return when (value.toString().lowercase(Locale.getDefault())) {
                "true" -> true
                "false" -> false
                else -> null
            }
        }
    }
}