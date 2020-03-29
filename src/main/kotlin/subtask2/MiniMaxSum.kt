package subtask2

class MiniMaxSum {

    fun getResult(input: IntArray): IntArray {

        val sortedInput = input.sortedArray()

        val summingItemsCount = sortedInput.size - 1

        val minFourElementsSum = sortedInput.take(summingItemsCount).sum()

        val maxFourElementsSum = sortedInput.takeLast(summingItemsCount).sum()

        return intArrayOf(minFourElementsSum, maxFourElementsSum)
    }
}
