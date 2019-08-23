package com.xup.interviewguide.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BalloonScore {
	
	public static int maxCores(int[] cores) {
		int len = cores.length;
		int[][] dp = new int[len][len];
		for (int k = 2; k < len; k++)
			for (int i = 0; i + k < len; i++)
				for (int j = i + 1; j < i + k; j++)
					dp[i][i + k] = Math.max(dp[i][i + k],
							dp[i][j] + dp[j][i + k] + cores[i] * cores[j] * cores[i + k]);
		return dp[0][len - 1];
	}
	
	public static int maxCores2(int[] cores) {
		int len = cores.length;
		int[][] dp = new int[len][len];
		for (int i = 0; i < len; i++)
			for (int j = i - 2; j >= 0; j--)
				for (int k = j + 1; k < i; k++)
					dp[i][j] = Math.max(dp[i][j],
							dp[k][j] + dp[i][k] + cores[i] * cores[j] * cores[k]);
		return dp[len - 1][0];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = br.readLine().split(" ");
		int n = Integer.parseInt(strs[0]);
		if (n == 0) {
			System.out.println(0);
			return;
		}
		strs = br.readLine().split(" ");
		int[] cores = new int[n + 2];
		cores[0] = cores[n + 1] = 1;
		for (int i = 1; i < cores.length - 1; i++)
			cores[i] = Integer.parseInt(strs[i - 1]);
		System.out.println(maxCores2(cores));
	}

}
