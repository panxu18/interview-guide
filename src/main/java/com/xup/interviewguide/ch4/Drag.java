package com.xup.interviewguide.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Drag {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = br.readLine().split(" ");
		int m = Integer.parseInt(strs[0]);
		int n = Integer.parseInt(strs[1]);
		
		int[][] map = new int[m][n];
		for (int i = 0; i < m; i++) {
			strs = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(strs[j]);
			}
		}
		
		int[] dp = new int[n];
		dp[n - 1] = Math.max(1, 1 - map[m - 1][n - 1]);
		for (int i = n - 2; i >= 0; i--)
			dp[i] = Math.max(1, dp[i + 1] - map[m - 1][i]);
		
		for (int i = m - 2; i >= 0; i--) {
			dp[n - 1] = Math.max(1, dp[n - 1] - map[i][n - 1]);
			for (int j = n - 2; j >= 0; j--)
				dp[j] = Math.max(1, Math.min(dp[j + 1], dp[j]) - map[i][j]);
		}
		
		System.out.println(dp[0]);
	}
}
