package com.xup.interviewguide.ch1;

import java.util.Stack;

public class VisibleMountain {
	
	/*
	 * 每个山峰能看到其他山峰的范围由它两边比它高的山峰确定，比如说山峰i高度为2，
	 * 它逆时针和顺时针方向比它高的山峰分别是山峰j高度为3和山峰k高度为4。
	 * 那么在区域[j,k]之间的山峰，除了一种情况之外，都可以被山峰i看到。不能被
	 * 山峰i看到的山峰是其高度比山峰i小，同时被其他比它高的山峰包围。因此，
	 * 如果在区域[j,k]中没有比山峰i低的山峰，那么区域内所有山峰都应该可以被山峰i
	 * 看到。
	 * 
	 * 根据上面的分析，如果我们从局部高度最低的山峰i开始，查找它能看到的山峰的范围，计算
	 * 山峰i以及和它同样高度的山峰构成的可见对，然后 将山峰i以及和它同样高度的山峰
	 * 移除（以后不在考虑）。再查找下一个局部高度最低的山峰，重复上面操作。
	 * 
	 * 如果从任意一个山峰i开始，沿着顺时针方向查找局部高度最低的山峰。如果后面的山峰i+1
	 * 高度比当前山峰i高度高，那么山峰i就是一个局部高度最低的山峰。其顺时针方向边界为i+1,
	 * 逆时针方向的边界为逆时针方向高度比它高的山峰。所以，如果山峰沿着顺时针时由高到低出现，
	 * 那么其右边界就是其一个山峰或者没有更高的山峰。因此，从高度最高的山峰开始，
	 * 沿着顺时针方向找局部高度最低的山峰，没有找到局部高度最低的山峰之前，将所有山峰都以
	 * 非递增的方法保存在单调序列，以非递增栈作为单调序列。
	 * 
	 * 因为数组是一个环，为了减少代码分支，将起始的最高山峰作为最后一个元素入栈，从而可以将
	 * 栈中非最大元素全部出栈。
	 * 如果找到一个局部最低的山峰i以及其可见山峰范围内的个数为k,如果左右边界对应的山峰不是
	 * 同一个山峰，那么可见山峰对数目为k*(k-1)/2 + 2 * k，否则，可见
	 * 山峰的对数目为k*(k-1)/2 + 1 * k

	 * 去除最后入栈的辅助元素后，最后剩下的最高山峰范围个数为k，那么可见山峰对数目为k*(k-1)/2
	 */
	public int getVisebleNums(int[] arr) {
		if (arr == null || arr.length <= 1)
			return 0;
		Stack<Integer> stack = new Stack<>();
		int count = 0;
		//查找最高山峰
		int start = 0;
		for (int i = 0; i < arr.length; i++)
			start = arr[i] > arr[start] ? i : start;
		int roundCount = 0;
		//最后将第一个元素入栈，是为了构成环
		while (roundCount <= arr.length) {
			int index = (start + roundCount) % arr.length;
			//找到局部高度最低山峰
			while (!stack.isEmpty() 
					&& arr[stack.peek()] < arr[index]) {
				int min = arr[stack.peek()];
				int range = 0;
				while (!stack.isEmpty() && arr[stack.peek()] == min) {
					stack.pop();
					range++;
				}
				// 判断左边的最高山峰和右边最高山峰是否是同一个山峰
				if (stack.peek() != index)
					count += (range * (range + 3) >> 1); 
				else
					count += (range * (range + 1) >> 1); 
			}
			stack.add(index);
			roundCount++;
		}
		stack.pop(); // 辅助元素出栈
		//最后剩下的最高山峰
		return count + ((stack.size() * (stack.size() - 1)) >> 1);
	}

	public static void main(String[] args) {
		System.out.println(new VisibleMountain().getVisebleNums(new int[] {
			8,5}));
	}
}
