package com.xup.interviewguide.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class SpliteListWithEOR {
	
	static int process(int[] arr) {
		HashMap<Integer, Object> map = new HashMap<>();
		
		int ret = 0;
		int eor = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[0] == 0 || (eor ^= arr[i]) == 0 || map.containsKey(eor)) {
				ret++;
				map.clear();
				eor = 0;
			}
			else 
				map.put(eor, 1);
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = br.readLine().split(" ");
		int n = Integer.parseInt(strs[0]);
		if (n == 0) {
			System.out.println("0");
		}
		strs = br.readLine().split(" ");
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(strs[i]);
		System.out.println(process(arr));
	}
}
