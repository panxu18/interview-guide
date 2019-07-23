package com.xup.interviewguide.ch1;

import java.util.LinkedList;

public class SublistNums {
	
	/*
	 * 从区域[0,0]开始，扩大区域的右边界，检查新区域是否满足条件，如果满足条件，
	 * 则增加的子数组的数量为当前区域的长度。如果不满足条件，则将左边界右移，
	 * 缩小区域范围，直到区域满足条件，然后增加的子数组的数量为当前区域的长度。
	 * 
	 * 要计算每个区域[i,j]是否满足要求，需要计算区域的最大值和最小值，
	 * 以计算最大值为例，扩大区域到j+1， 那么需要将a[j+1]与max比较，如果a[j+1]
	 * 大于max，那么区域[i,j+1]中的最大值为a[j+1]。判断区域[i,j+1]是否满足
	 * 条件，如果a[j+1] - min > num，那么需要缩小区域范围到[i+k,j+1]。
	 * 增加的子数组的数量为当前区域的长度。这时需要计算区域[i+k, j+1]的最小值，
	 * 为了能在O(1)时间内得到最大值，采用单调队列维持区域[i,j+1]的最小值。
	 */
	public int getNum (int[] arr, int num) {
		if (arr == null || arr.length <= 0 || num < 0)
			return 0;
		LinkedList<Integer> qMax = new LinkedList<>();
		LinkedList<Integer> qMin = new LinkedList<>();
		int start = 0;
		int end = 0;
		int count = 0;
		for (; end < arr.length; end++) {
			// 维护最大队列
			while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[end])
				qMax.pollLast();
			qMax.addLast(end);
			// 维护最小队列
			while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[end])
				qMin.pollLast();
			qMin.addLast(end);
			// 最大值与最小值的差大于num，更新最大值队列或最小值队列，同时更新区域左边界
			if (arr[qMax.peekFirst()] - arr[qMin.peekFirst()] > num) {
				LinkedList<Integer> temp;
				if (qMax.peekFirst() > qMin.peekFirst()) 
					temp = qMin;
				else
					temp = qMax;
				while (!temp.isEmpty() 
						&& arr[qMax.peekFirst()] - arr[qMin.peekFirst()] > num)
					start = temp.pollFirst();
				start += 1;
			}
			count += (end - start + 1);
		}
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println(new SublistNums().getNum(new int[] {1,1,1,1,1,1}, 2));
	}
}
