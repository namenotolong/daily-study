package com.huyong.study.algorithm.leetcode.dinamic;

/**
 * @author huyong
 * 0/1背包问题
 * 如果我们逆向枚举，（dp的index从大到小，进行dp更新。）因为 j > j-w，同一个i的dp[j]会比dp[j-w]先更新，所以dp[j]的更新用的就是上一轮的dp[j-w]。即i的dp[j] 用的是i-1的dp[j-w]更新。 这样不就等价于我们空间优化前的式子， dp[i][j]=dp[i-1][j-w]了嘛。
 * 那如果是正向枚举，（dp的index从小到大，进行dp更新），因为j-w<j, 所以，在同一个i中，dp[j-w]会比dp[j]先更新。所以更新dp[j]时，用到的dp[j-w]一定是同一个i的。 即i的dp[j]是用i的dp[j-w]更新。这样就等价于优化前的式子，dp[i][j] = dp [i][j-w] 了！
 */
public class PackageZeroOne {

    public static int resolve(int[] weight, int[] v, int size) {
        int[][] dep = new int[weight.length + 1][size + 1];
        for (int i = 0; i < size; i++) {
            dep[0][i] = 0;
        }
        for (int i = 1; i <= weight.length; i++) {
            for (int j = 0; j <= size; j++) {
                if (j > weight[i - 1]) {
                    dep[i][j] = Math.max(dep[i - 1][j], dep[i - 1][j - weight[i - 1]] + v[i - 1]);
                } else {
                    dep[i][j] = dep[i - 1][j];
                }
            }
        }
        for (int i = 0; i < dep.length; i++) {
            for (int j = 0; j < dep[i].length; j++) {
                System.out.print(dep[i][j] + " ");
            }
            System.out.println();
        }
        return dep[weight.length][size];
    }

    public static void main(String[] args) {
        int[] weight = {1,3,5,7,9};
        int[] v = {1,2,4,5,6};
        System.out.println(resolve(weight, v, 10));;
    }

}
