package com.xup.interviewguide.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HanoiProblem {
	
	private static int process(int[] arr) {
		if (arr.length == 0)
			return 0;
		int[] count = new int[arr.length];
		count[0] = 1;
		for (int i = 1; i < arr.length; i++)
			count[i] = (count[i - 1] << 1) % 1000000007;
		int from = 1;
		int mid = 2;
		int to = 3;
		int temp = 1;
		int sum = 0;
		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] == from) {
				temp = to;
				to = mid;
				mid = temp;
			} else if (arr[i] == to) {
				sum += count[i] % 1000000007;
				temp = from;
				from = mid;
				mid = temp;
			} else
				return -1;
		}
		return sum % 1000000007;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 8 * 1024 * 1024);
		String[] strs = br.readLine().split(" ");
		int n = Integer.parseInt(strs[0]);
		if (n == 0) {
			System.out.println(0);
			return;
		}
		int[] arr = new int[n];
		strs = br.readLine().split(" ");
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(strs[i]);
		System.out.println(process(arr));
	}

}
