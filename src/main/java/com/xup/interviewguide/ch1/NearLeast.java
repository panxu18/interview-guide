package com.xup.interviewguide.ch1;

import java.util.ArrayList;
import java.util.Stack;

public class NearLeast {
	
	/*
	 * 如果要查找元素e左边最近的最小元素，需要向左边遍历每个元素，直到找到一个
	 * 小于e的元素。如果原序列是一个单调递减序列，那么每个元素都需要向前遍历直到
	 * 第一个元素，才能发现没有比它小的元素。那么时间复杂度为O(n2)。
	 * 因此，想要降低时间复杂度，需要缩小比较范围。前面已经直到，如果需要比较的元素
	 * 单调递减，那么需要一直比较到第一个元素，所以不能出现a[i] > a[i + 1]这种偏序。
	 * 因此需要构建一个单调递增序列，利用栈保存一个递增数列对应的索引序列，对于每个元素e，
	 * 如果栈顶元素比e小， 那么e左边的最小元素就是栈顶元素，如果栈顶元素比e大，
	 * 那么将栈顶元素出栈， 直到栈顶元素小于e。
	 * 
	 * 如果出现了偏序a[i] > a[i + 1]，元素将会出栈，这表示新入栈的元素就是出栈元素
	 * 右边最临近的最小值
	 */
	public ArrayList<ArrayList<Integer>> getNearLeast(int[] arr) {
		ArrayList<Integer> result = null;
		ArrayList<ArrayList<Integer>> resultAll = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				resultAll.get(stack.peek()).add(i);
				stack.pop();
			}
			result = new ArrayList<>(2);
			if (stack.isEmpty())
				result.add(-1);
			else
				result.add(stack.peek());
			resultAll.add(result);
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			resultAll.get(stack.peek()).add(1, -1);
			stack.pop();
		}
		return resultAll;
	}
	
	public static void main(String[] args) {
		System.out.println(new NearLeast().getNearLeast(new int[]{3,4,1,5,6,2,7}));
	}
}
