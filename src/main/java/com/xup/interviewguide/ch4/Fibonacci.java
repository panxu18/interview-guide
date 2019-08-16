package com.xup.interviewguide.ch4;

import java.util.Scanner;

public class Fibonacci {

	/*
	 * Fibonacci序列
	 */
	public static long getFibonacci(long n) {
		long[][] res = {{1, 0}};
		long[][] fact = {{1, 1}, {1, 0}};
		return getFibonacci(n, res, fact);
	}
	
	/*
	 * 求上台阶方法的总数
	 */
	public static long getSteps(long n) {
		long[][] res = {{1, 1}};
		long[][] fact = {{1, 1}, {1, 0}};
		return getFibonacci(n, res, fact);
	}
	
	/*
	 * 求奶牛总数
	 */
	public static long getCrowNums(long n) {
		long[][] fact = {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        long[][] res = {{1,1,1}};
        return getFibonacci(n, res, fact);
	}
	
	public static long getFibonacci(long n, long[][] res, long[][] fact) {
		long[][] init = getUnitMatrix(fact.length);
		n -= 1;
		while (n > 0) {
			if ((n & 1) != 0)
				init = matMutply(init, fact);
			fact = matMutply(fact, fact);
			n >>= 1;
		}
		res = matMutply(res, init);
		return res[0][0];

	}
	
	/*
	 * 单位矩阵
	 */
	private static long[][] getUnitMatrix(int n) {
		long[][] m = new long[n][n];
		while (--n >= 0)
			m[n][n] = 1;
		return m;
	}

	/*
	 * 矩阵相乘
	 */
	private static long[][] matMutply(long[][] m1, long[][] m2) {
		int mod = 1_000_000_007;
		long[][] res = new long[m1.length][m2[0].length];
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m2[0].length; j++) {
				for (int k = 0; k < m2.length; k++) {
					res[i][j] += m1[i][k] * m2[k][j] % mod;
					res[i][j] %= mod;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while (input.hasNext()) {
			System.out.print(getFibonacci(input.nextLong()));
		}
	}
}
