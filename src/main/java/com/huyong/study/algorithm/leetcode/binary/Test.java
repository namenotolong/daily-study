package com.huyong.study.algorithm.leetcode.binary;

public class Test {

    /**
     * 不用乘除做整数乘法运算
     */
    int multi(int a, int b){
        int count = 0;
        int res = 0;
        while (b != 0) {
            if ((b & 1) == 1) {
                res += a << count;
            }
            b >>= 1;
            ++count;
        }
        return res;
    }

    int dvi(int a, int b) {
        boolean flag = (a < 0 && b < 0) || (a > 0 && b > 0);
        a = Math.abs(a);
        b = Math.abs(b);
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            int temp = a >> i;
            if (temp >= b) {
                res |= (1 << i);
                a -= b << i;
            }
        }
        if (flag) {
            return res;
        }
        return -res;
    }

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.multi(4,5));
        System.out.println(test.multi(41,5));
        System.out.println(test.multi(41,51));
        System.out.println(test.multi(-41,51));
        System.out.println(test.dvi(-4,-2));
        System.out.println(test.dvi(-4,2));
        System.out.println(test.dvi(4,2));
        System.out.println(test.dvi(4,3));
    }

}
