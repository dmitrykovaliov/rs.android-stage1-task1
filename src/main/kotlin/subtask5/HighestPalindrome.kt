package subtask5

class HighestPalindrome {

    fun highestValuePalindrome(n: Int, k: Int, digitString: String): String {

        if (digitString.isBlank()) throw IllegalArgumentException("Incorrect digitString parameter")

        val digitStringLength = if (n != digitString.length) digitString.length else n

        val leftHalfList = getDigitsList(digitStringLength, digitString)
        val rightHalfReversedList = getDigitsList(digitStringLength, digitString.reversed())

        val sortedMetaDataList = getMetaDataList(leftHalfList, rightHalfReversedList)
            .sortedBy { it.mirrorElementsSum }
            .toMutableList()

        var changes = 0
        while (k - changes > 0) {
            val isOdd = k % 2 == 1
            val isLastChange = k - changes == 1
            if (isOdd && isLastChange) {
                replaceReflectDigitToMax(leftHalfList, rightHalfReversedList, sortedMetaDataList)
            } else {
                replaceReflectDigitsToNine(leftHalfList, rightHalfReversedList, sortedMetaDataList)
            }
            changes++
        }
        val resultString =
            buildResultString(leftHalfList, rightHalfReversedList, digitStringLength, digitString)
        if (isPalindrome(resultString)) {
            return resultString
        }
        return IMPOSSIBLE_TO_CREATE_PALINDROME_RESULT
    }

    private fun replaceReflectDigitsToNine(
        leftHalfList: MutableList<String>,
        rightHalfReversedList: MutableList<String>,
        sortedMetaDataList: MutableList<DigitData>
    ) {
        val iterator = sortedMetaDataList.iterator()
        if (iterator.hasNext()) {
            val nextItem = iterator.next()
            val key = nextItem.itemIndex

            if (nextItem.mirrorElementsDifference != 0) {
                leftHalfList[key] = "9"
                rightHalfReversedList[key] = "9"
            }
            sortedMetaDataList.removeAt(0)
        }
    }

    private fun replaceReflectDigitToMax(
        leftHalfList: MutableList<String>,
        rightHalfReversedList: MutableList<String>,
        sortedDiscoverList: MutableList<DigitData>
    ) {
        val firstItem = sortedDiscoverList.maxBy { it.mirrorElementsDifference }!!
        val index = firstItem.itemIndex
        val firstValueDifference = firstItem.mirrorElementsDifference
        if (firstValueDifference > 0) {
            rightHalfReversedList[index] = leftHalfList[index]
        }
        if (firstValueDifference < 0) {
            leftHalfList[index] = rightHalfReversedList[index]
        }
    }

    private fun getDigitsList(
        digitStringLength: Int,
        digitString: String
    ): MutableList<String> = digitString.substring(0, digitStringLength / 2).toList()
        .map { it.toString() }
        .toMutableList()

    private fun buildResultString(
        leftHalfList: MutableList<String>,
        rightHalfReversedList: MutableList<String>,
        digitStringLength: Int,
        digitString: String
    ): String {
        val allList = mutableListOf<String>()
        allList.addAll(leftHalfList)
        if (digitStringLength % 2 == 1) {
            val center = digitString[digitStringLength / 2].toString()
            allList.add(center)
        }
        allList.addAll(rightHalfReversedList.reversed())

        return allList.joinToString("")
    }

    private fun getMetaDataList(
        leftHalfList: MutableList<String>,
        rightHalfReversedList: MutableList<String>
    ): MutableList<DigitData> {
        val metaDataList = mutableListOf<DigitData>()

        for ((index, character) in leftHalfList.withIndex()) {
            metaDataList.add(
                DigitData(
                    index,
                    character.toInt() - rightHalfReversedList[index].toInt(),
                    character.toInt() + rightHalfReversedList[index].toInt()
                )
            )

        }
        return metaDataList
    }

    private fun isPalindrome(digitString: String): Boolean {
        val halfDigitString = digitString.substring(0, digitString.length / 2)
        val halfReversedDigitString = digitString.reversed().substring(0, digitString.length / 2)

        return halfDigitString == halfReversedDigitString
    }

    private data class DigitData(
        val itemIndex: Int,
        val mirrorElementsDifference: Int,
        val mirrorElementsSum: Int
    )

    companion object {
        const val IMPOSSIBLE_TO_CREATE_PALINDROME_RESULT = "-1"
    }
}
