package com.xup.interviewguide.ch1;

import java.util.Stack;

public class MaxRecSize {
	
	public int getMaxRecSize(int[][] map) {
		if (map == null || map.length <= 0 || map[0].length <= 0)
			return 0;
		int[] height = new int[map[0].length];
		int maxArea = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < height.length; j++)
				height[j] = map[0][j] == 0 ? 0 : height[j] + map[0][j];
			maxArea = Integer.max(maxArea, getMaxRecSizeCore(height));
		}
		return maxArea;
	}
	
	/*
	 * 给定不同的高度柱形图，求其中的最大连续区域。
	 * 对每一个高度为h的柱需要向其左边后右边遍历，检查以高度h能获得最大面积。
	 * 例如，左边的柱高度大于h，那么需要一直向左找到第一个高度小于h的柱，才能
	 * 确定高度h对应的最大区域的起始位置。
	 * 如果使用这种比较方法会存在很多重复的比较，例如，第i个柱的高度为h，
	 * 向前找到柱i'的高度比h小，对于第i+1个柱高度为h'（h' < h），应该从柱i'
	 * 开始比较，因为中间的柱的高度都大于h大于h'。
	 * 这种多余的比较是由于比较序列中存在H(i) > H(j),i < j),因此构造单调增
	 * 序列就可以消除冗余的比较。
	 */
	private int getMaxRecSizeCore(int[] height) {
		Stack<Integer> stack = new Stack<>();
		int maxArea = 0;
		for (int i = 0; i < height.length; i++) {
			while (!stack.isEmpty() && height[stack.peek()] > height[i]) {
				int start = stack.pop();
				maxArea = Integer.max(maxArea, height[start] * (i - start));
			}
			if (height[i] > 0 
					&& (stack.isEmpty() || height[stack.peek()] < height[i]))
				stack.push(i);
		}
		while (!stack.isEmpty()) {
			int start = stack.pop();
			maxArea = Integer.max(maxArea, 
					height[start] * (height.length - start));
		}
		return maxArea;
	}
	
	public static void main(String[] args) {
		System.out.println(new MaxRecSize().getMaxRecSize(
				new int[][]{
					{1,0,1,1},{1,1,1,1},{1,1,1,0}
				}));
	}
}
