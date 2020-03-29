package subtask3

class BillCounter {

    fun calculateFairlySplit(bill: IntArray, k: Int, b: Int): String {

        if (k !in 0..bill.lastIndex) throw IllegalArgumentException("Incorrect k parameter")

        val billAmount = (bill.sum() - bill[k]) / 2

        val compensationAmount = b - billAmount

        if (compensationAmount > 0) {
            return compensationAmount.toString()
        }
        return "Bon Appetit"
    }
}
