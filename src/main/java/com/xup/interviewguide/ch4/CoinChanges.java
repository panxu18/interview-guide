package com.xup.interviewguide.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CoinChanges {

	public static int process(int[] coin, int aim) {
		int[]dp = new int[aim + 1];
		dp[0] = 1;
		for (int i = 0; i < coin.length; i++) {
			for (int j = coin[i]; j <= aim; j++) {
				dp[j] = (dp[j] + dp[j- coin[i]]) % 1_000_000_007;
			}
		}
		return dp[aim];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = br.readLine().split(" ");
		int n = Integer.parseInt(strs[0]);
		int aim = Integer.parseInt(strs[1]);
		strs = br.readLine().split(" ");
		int[] coin = new int[n];
		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(strs[i]);
		}
		System.out.println(process(coin, aim));
	}
	
}
