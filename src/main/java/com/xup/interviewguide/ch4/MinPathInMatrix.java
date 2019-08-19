package com.xup.interviewguide.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinPathInMatrix {
	
	public static int minPath(int[][] m) {
		int row = m.length - 1;
		int col = m[0].length - 1;
		int[] dp = new int[col + 1];
		dp[col] = m[row][col];
		for (int i = col - 1; i >= 0; i--)
			dp[i] = dp[i + 1] + m[row][i];
		for (int i = row - 1; i >= 0; i--) {
			dp[col] = dp[col] + m[i][col];
			for (int j = col - 1; j >= 0; j--)
				dp[j] = Math.min(dp[j], dp[j + 1]) + m[i][j];
		}
		return dp[0];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] strs = br.readLine().split(" ");
		int row = Integer.parseInt(strs[0]);
		int col = Integer.parseInt(strs[1]);
		int[][] m = new int[row][col];
		for (int i = 0; i < row; i++) {
			strs = br.readLine().split(" ");
			for (int j = 0; j < col; j++)
				m[i][j] = Integer.parseInt(strs[j]);
		}
		System.out.println(minPath(m));
				
	}
}
