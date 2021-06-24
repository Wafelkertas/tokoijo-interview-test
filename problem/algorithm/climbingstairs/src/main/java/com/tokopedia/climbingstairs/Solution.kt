package com.tokopedia.climbingstairs

object Solution {
    fun climbStairs(n: Int): Int {
        // TODO, return in how many distinct ways can you climb to the top. Each time you can either climb 1 or 2 steps.
        // 1 <= n < 90

        return fib(n+1)
    }

    private fun fib(i: Int): Int{
        if(i <= 1)
            return i
        return fib(i-1) + fib(i-2)
    }
}
