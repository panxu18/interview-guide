package com.xup.interviewguide.ch3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReConstructBSTree {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int n = Integer.parseInt(strs[0]);
        if (n == 0)
            return;
        strs = br.readLine().split(" ");
        int[] list = new int[n];
        for (int i = 0; i < n; i++)
            list[i] = Integer.parseInt(strs[i]);
        System.out.println(constructBSTree(list, 0, n));
	}
	
	/**
	 * 因为后续遍历，所以区间分为三部分，第一部分为左子树序列，
	 * 第二部分是右子树序列，最后一个元素为第三部分即根节点。
	 * @param list 
	 * @param start 区间起始索引
	 * @param end 区间终止索引（不包含）
	 * @return
	 */
	private static boolean constructBSTree(int[] list, int start, int end) {
		if (list == null || start >= end || end > list.length)
			return true;
		int valRoot = list[end - 1];
		int mid = start;
		while (mid < end - 1 && list[mid] < valRoot)
			mid++;
		int t = mid;
		while (t < end -1 && list[t] > valRoot)
			t++;
		return t == end - 1
				&& constructBSTree(list, start, mid)
				&& constructBSTree(list, mid, end - 1);
	}

}
