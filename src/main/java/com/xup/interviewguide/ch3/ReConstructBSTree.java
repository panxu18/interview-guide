package com.xup.interviewguide.ch3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReConstructBSTree {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int len;
		ArrayList<Integer> list;
		while (input.hasNext()) {
			len = input.nextInt();
			list = new ArrayList<>(len);
			while (len-- > 0) {
				list.add(input.nextInt());
			}
			System.out.println(constructBSTree(list, 0, list.size()));
		}
		
	}
	
	/**
	 * 因为后续遍历，所以区间分为三部分，第一部分为左子树序列，
	 * 第二部分是右子树序列，最后一个元素为第三部分即根节点。
	 * @param list 
	 * @param start 区间起始索引
	 * @param end 区间终止索引（不包含）
	 * @return
	 */
	private static boolean constructBSTree(ArrayList<Integer> list, int start, int end) {
		if (list == null || start >= end || end > list.size())
			return true;
		int valRoot = list.get(end - 1);
		int mid = start;
		while (mid < end - 1 && list.get(mid) < valRoot)
			mid++;
		int t = mid;
		while (t < end -1 && list.get(t) > valRoot)
			t++;
		return t == end - 1
				&& constructBSTree(list, start, mid)
				&& constructBSTree(list, mid, end - 1);
	}

}
