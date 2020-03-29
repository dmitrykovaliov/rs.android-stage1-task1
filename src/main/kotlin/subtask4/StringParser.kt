package subtask4

class StringParser {

    fun getResult(inputString: String): Array<String> {

        if (inputString.isBlank()) {
            throw IllegalArgumentException("Incorrect inputString parameter")
        }
        val inputStringList = inputString.toList()
        val resultList = mutableListOf<String>()

        for ((index, character) in inputStringList.withIndex()) {

            BracketType.getBracketType(character)?.let {
                val subArray = inputStringList.subList(index, inputStringList.size)
                val subString = getSubstring(subArray, it)
                if (subString.isNotBlank()) {
                    resultList.add(subString)
                }
            }
        }
        return resultList.toTypedArray()
    }

    private fun getSubstring(array: List<Char>, bracketType: BracketType): String {

        var endIndex = 0
        var count = 0

        for ((index, character) in array.withIndex()) {
            when (character) {
                bracketType.start -> count++
                bracketType.end -> count--
            }
            if (count == 0) {
                endIndex = index
                break
            }
        }
        if (endIndex > 1) {
            return array.subList(1, endIndex).joinToString("")
        }
        return ""
    }

    enum class BracketType(val start: Char, val end: Char) {

        ANGLE_BRACKET('<', '>'),
        SQUARE_BRACKET('[', ']'),
        ROUND_BRACKET('(', ')');

        companion object {
            fun getBracketType(checkCharacter: Char): BracketType? {

                return when (checkCharacter) {
                    ANGLE_BRACKET.start -> ANGLE_BRACKET
                    SQUARE_BRACKET.start -> SQUARE_BRACKET
                    ROUND_BRACKET.start -> ROUND_BRACKET
                    else -> null
                }
            }
        }
    }
}
