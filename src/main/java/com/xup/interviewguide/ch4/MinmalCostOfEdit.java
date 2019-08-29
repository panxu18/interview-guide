package com.xup.interviewguide.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinmalCostOfEdit {
	static int cost(String str1, String str2, int ic, int dc, int rc) {
		if (str1 == null || str2 == null)
			return 0;
		int[] dp = new int[str2.length() + 1];
		dp[0] = 0;
		for (int i = 1; i <= str2.length(); i++)
			dp[i] = dp[i - 1] + ic;
		for (int i = 1; i <= str1.length(); i++) {
			int last = dp[0];
			dp[0] = i * dc;
			for(int j = 1; j <= str2.length(); j++) {
				int temp = dp[j];
				dp[j] = Math.min(dp[j] + dc, dp[j - 1] + ic);
				if (str1.charAt(i - 1) == str2.charAt(j - 1))
					dp[j] = Math.min(dp[j], last);
				else
					dp[j] = Math.min(dp[j], last + rc);
				last = temp;
			}
		}
		return dp[str2.length()];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		String[] strs = br.readLine().split(" ");
		int[] costs = new int[3];
		for (int i = 0; i < costs.length; i++)
			costs[i] = Integer.parseInt(strs[i]);
		System.out.println(cost(str1, str2, costs[0], costs[1], costs[2]));
		
	}
}
