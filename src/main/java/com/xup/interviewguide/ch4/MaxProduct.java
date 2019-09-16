package com.xup.interviewguide.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaxProduct {
	
	static double process(double[] arr) {
		if (arr == null  || arr.length == 0)
			throw new IllegalArgumentException();
		double max = 1;
		double min = 1;
		double res = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < arr.length; i++) {
			double temp = max;
			max = Math.max(arr[i], Math.max(max * arr[i], min * arr[i]));
			min = Math.min(arr[i], Math.min(min * arr[i], temp * arr[i]));
			res = Math.max(res, max);
		}
		return res;
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
		double[] arr = new double[n];
		for (int i = 0; i < n; i++)
			arr[i] = Double.parseDouble(strs[i]);
		System.out.println(String.format("%.2f", process(arr)));
	}
}
