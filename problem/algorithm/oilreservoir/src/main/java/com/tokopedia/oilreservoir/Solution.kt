package com.tokopedia.oilreservoir


object Solution {
    fun collectOil(height: IntArray): Int {
        // TODO, return the amount of oil blocks that could be collected
        var accumulator = 0


        val arraySize = height.size - 1

        val left = IntArray(arraySize)
        val right = IntArray(arraySize)


        left[0] = height[0]
        for (i in 1 until arraySize) left[i]  = left[i - 1].coerceAtLeast(height[i])

        right[arraySize -1] = height[arraySize - 1]
        for (i in arraySize - 2 downTo 0) right[i] = right[i + 1].coerceAtLeast(height[i])

        for (i in 0 until arraySize)accumulator += left[i].coerceAtMost(right[i]) - height[i]

        return accumulator
    }
}
