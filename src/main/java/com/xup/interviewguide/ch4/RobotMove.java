package com.xup.interviewguide.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RobotMove {
	
	/**
	 * 
	 * @param n 移动范围
	 * @param m 当前位置
	 * @param k 剩余步数
	 * @param p 目标位置
	 * @return 所有移动方案的总数
	 */
	public static final int getWays(int n, int m, int k, int p) {
		int[] dp = new int[n];
		dp[p - 1] = 1;
		for (int i = 1, len = k + 1; i < len; i++) {
			int tempFirst = dp[1];
			int tempLast = dp[n - 2];
			int leftTemp = dp[0];
			for (int j = 1, lenPos = n - 1; j < lenPos; j++) {
				int temp = dp[j];
				dp[j] = (leftTemp + dp[j + 1]);
				dp[j] %= 1000000007;
				leftTemp = temp;
			}
			dp[0] = tempFirst;
			dp[n - 1] = tempLast;
		}
		return dp[m - 1];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = br.readLine().split(" " );
		int n = Integer.parseInt(strs[0]);
		int m = Integer.parseInt(strs[1]);
		int k = Integer.parseInt(strs[2]);
		int p = Integer.parseInt(strs[3]);
		System.out.println(getWays(n, m, k, p));
	}
}
