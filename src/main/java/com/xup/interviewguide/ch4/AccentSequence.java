package com.xup.interviewguide.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AccentSequence {
	
	public static int[] process(int[] list) {
		int len = list.length;
		int[] dp = new int[len];
		int[] b = new int[len];
		int max = 0;
		for (int i = 0; i < len; i++) {
			int tmp = replace(b, max, list[i]);
			if (tmp > max)
				max = tmp;
			dp[i] = tmp;
		}
		int index = len - 1;
		int[] ret = new int[max];
		int indexRet = max - 1;
		while (index >= 0 && indexRet >= 0) {
			if (dp[index] == indexRet + 1) {
				ret[indexRet] = list[index];
				indexRet--;
			}
			index--;
		}
		return ret;
	}
	
	private static int replace(int[] b, int len, int k) {
		int start = 0;
		int end = len - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (b[mid] < k)
				start = mid + 1;
			else
				end = mid - 1;
		}
		b[start] = k;
		return start + 1;
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
		int[] list = new int[n];
		for (int i = 0; i < n; i++)
			list[i] = Integer.parseInt(strs[i]);
		int[] result = process(list);
		StringBuilder sb = new StringBuilder();
		int index = 0;
		while (index < result.length - 1) {
			sb.append(result[index]);
			sb.append(" ");
			index++;
		}
		sb.append(result[index]);
		System.out.println(sb.toString());
	}
}
