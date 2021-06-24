package com.tokopedia.minimumpathsum

import java.util.ArrayList

object Solution {
    fun minimumPathSum(matrix: Array<IntArray>): Int {
        // TODO, find a path from top left to bottom right which minimizes the sum of all numbers along its path, and return the sum

        val m = matrix.size
        val n = matrix[m-1].size
        for(i in 1 until m){
            matrix[i][0] += matrix[i-1][0]
        }
        for(j in 1 until n){
            matrix[0][j] += matrix[0][j-1]
        }
        for(i in 1 until m){
            for(j in 1 until n){
                matrix[i][j] += matrix[i - 1][j - 1].coerceAtMost(
                        matrix[i - 1][j].coerceAtMost(matrix[i][j - 1])
                )
            }
        }

        return matrix[m - 1][n - 1]

    }

}
