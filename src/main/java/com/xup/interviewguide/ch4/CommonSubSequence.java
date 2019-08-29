package com.xup.interviewguide.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommonSubSequence {

	static String process(String str1, String str2) {
	if (str1 == null || str2 == null
			|| str1.length() == 0 || str2.length() == 0)
		 return "-1";
	int[][] dp = new int[str1.length()][str2.length()];
	dp[0][0] = str1.charAt(0) == str2.charAt(0) ? 1 : 0;
	for (int i = 1; i < str1.length(); i++) {
		dp[i][0] = Math.max(dp[i - 1][0], 
				str1.charAt(i) == str2.charAt(0) ? 1 : 0);
	}
	for (int i = 1; i < str2.length(); i++) {
		dp[0][i] = Math.max(dp[0][i - 1], 
				str1.charAt(0) == str2.charAt(i) ? 1 : 0);
	}
	int max = 0;
	for (int i = 1; i < str1.length(); i++) {
		for (int j = 1; j < str2.length(); j++) {
			dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
			if (str1.charAt(i) == str2.charAt(j))
				dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
			if (dp[i][j] > max) {
				max = dp[i][j];
			}
		}
	}
    if (max == 0)
        return "-1";
	char[] result = new char[max];
	int row = str1.length() - 1;
	int col = str2.length() - 1;
	while (row >= 0 && col >= 0 && max > 0) {
		if (row > 0 && dp[row][col] == dp[row - 1][col])
			row--;
		else if (col > 0 && dp[row][col] == dp[row][col - 1])
			col--;
		else {
			result[--max] = str1.charAt(row);
			row--;
			col--;
		}
	}
	return String.valueOf(result);
}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = new String[2];
		int i = 0;
		while (i < 2 && (strs[i++] = br.readLine()) != null);
		System.out.print(process(strs[0], strs[1]));
	}

}
