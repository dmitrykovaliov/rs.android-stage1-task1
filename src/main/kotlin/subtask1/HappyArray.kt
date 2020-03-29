package subtask1

class HappyArray {

    fun convertToHappy(sadArray: IntArray): IntArray {

        val happyArray = mutableListOf<Int>()

        sadArray.forEach { item ->
            happyArray.add(item)
            while (isPreviousSad(happyArray)) {
                removePrevious(happyArray)
            }
        }
        return happyArray.toIntArray()
    }


    private fun isPreviousSad(happyArray: MutableList<Int>): Boolean {

        val previousIndex = happyArray.lastIndex - 1
        if (previousIndex <= 0) {
            return false
        }
        return happyArray[previousIndex] > happyArray[previousIndex - 1] + happyArray[previousIndex + 1]
    }

    private fun removePrevious(happyArray: MutableList<Int>) {
        happyArray.removeAt(happyArray.lastIndex - 1)
    }

}
