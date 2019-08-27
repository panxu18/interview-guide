package com.xup.interviewguide.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommonSubString {
	
	static String process(String str1, String str2) {
		if (str1 == null || str2 == null
				|| str1.length() == 0 || str2.length() == 0)
			return "-1";
		
		int dp = 0;
		int max = 0;
		int index = 0;
		int row = 0;
		int col = str2.length() - 1;
		while (row < str1.length()) {
			int i = row;
			int j = col;
			dp = 0;
			while (i < str1.length() && j < str2.length()) {
				if (str1.charAt(i) != str2.charAt(j))
					dp = 0;
				else
					dp += 1;
				if (dp > max) {
					max = dp;
					index = i;
				}
				i++;
				j++;
			}
			if (col > 0)
				col--;
			else
				row++;
		}
		
		if (max == 0)
            return "-1";
		return str1.substring(index - max + 1, index + 1);
	}

//static String process(String str1, String str2) {
//	if (str1 == null || str2 == null
//			|| str1.length() == 0 || str2.length() == 0)
//		return "";
//	int[][] dp = new int[str1.length()][str2.length()];
//	for (int i = 0; i < str1.length(); i++) {
//		if (str1.charAt(i) == str2.charAt(0))
//			dp[i][0] = 1;
//	}
//	for (int i = 0; i < str2.length(); i++) {
//		if (str1.charAt(0) == str2.charAt(i))
//			dp[0][i] = 1;
//	}
//	
//	int max = 0;
//	int index = 0;
//	
//	for (int i = 1; i < str1.length(); i++) {
//		for (int j = 1; j < str2.length(); j++) {
//			if (str1.charAt(i) != str2.charAt(j))
//				dp[i][j] = 0;
//			else {
//				dp[i][j] = dp[i - 1][j - 1] + 1;
//			}
//			if (dp[i][j] > max) {
//				max = dp[i][j];
//				index = i;
//			}
//			}
//		}
//	}
//	return str1.substring(index - max + 1, index + 1);
//}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = new String[2];
		int i = 0;
		while (i < 2 && (strs[i++] = br.readLine()) != null);
		System.out.print(process(strs[0], strs[1]));
	}

}
