package com.xup.interviewguide.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringPermutations {
	
	static String valid(String str1, String str2, String aim) {
		if (str1 == null || str2 == null || aim == null
				|| str1.length() + str2.length() != aim.length())
			return "NO";
		String longStr = str1;
		String shortStr = str2;
		if (str1.length() < str2.length()) {
			longStr = str2;
			shortStr = str1;
		}
		boolean[] dp = new boolean[shortStr.length() + 1];
		dp[0] = true;
		for (int i = 1; i <= shortStr.length(); i++)
			dp[i] = dp[i - 1] & (aim.charAt(i - 1) == shortStr.charAt(i - 1));
		for (int i = 1; i <= longStr.length(); i++) {
			dp[0] = dp[0] & (aim.charAt(i - 1) == longStr.charAt(i - 1));
			for (int j = 1; j <= shortStr.length(); j++) {
				if ((dp[j] && longStr.charAt(i - 1) == aim.charAt(i + j - 1))
                      || (dp[j - 1] && shortStr.charAt(j - 1) == aim.charAt(i + j - 1))) {
                  dp[j] = true;
              }
			}
		}
		return dp[shortStr.length()] ? "YES" : "NO";
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		String aim = br.readLine();
		System.out.println(valid(str1, str2, aim));
	}
	
}
