package com.xup.interviewguide.ch1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 牛客网剑指offer第64题
 */
public class MaxInWindows {
	
	/**
	 * 每个元素会成为包含它的size个窗口的最大值的候选值，因此每个元素需要和后面的size-1个元素比较，
	 * 保留较大值候选值。当窗口内的size个元素都检查完之后，就得到了窗口内的最大值。
	 */
	public ArrayList<Integer> maxInWindows(int [] num, int size) {
		if (size <= 0 || size > num.length)
			return new ArrayList<>(0);
		for (int i = num.length - 1; i >= 0; i--)
			for (int j = 0; j < size && i + j < num.length; j++) // 向后比较并更新候选值
				if (num[i] > num[i + j])
					num[i + j] = num[i];
		return IntStream.of(num)
						.skip(size - 1)
						.boxed()
						.collect(Collectors.toCollection(ArrayList::new));
	}
	
	/**
	 * 前面的方法有一个问题，那就是如果对于元素7发现他比前面的所有元素都大，那么队列中的元素全部会被更新为7，
	 * 接下来一个元素5比7小，仍然要和队列中的所有元素进行比较，实际上这是不需要的，因为已经知道后面的元素
	 * 一定不小于7，所以可以不需要比较。
	 */
	public ArrayList<Integer> maxInWindows2(int [] num, int size) {
		if (size <= 0 || size > num.length)
			return new ArrayList<>(0);
		for (int i = num.length - 1; i >= 0; i--)
			for (int j = 1; j < size && i + j < num.length; j++) { // 向后比较并更新候选值
				if (num[i] > num[i + j])
					num[i + j] = num[i];
				else
					break;
			}
		return IntStream.of(num)
						.skip(size - 1)
						.boxed()
						.collect(Collectors.toCollection(ArrayList::new));
	}
	
	/**
	 * 第二种方法还有一个问题，那就遍历时元素依次增大，那么仍然需要遍历队列更新所有值，
	 * 只有将连续的相同值合并成一个区域，就可以减少比较次数。这里将队列中存储的最大值，
	 * 改为存储最大值对应的索引。索引可以表示一个区域，即以该元素结尾的窗口到以该元素开始的
	 * size个窗口。
	 */
	public ArrayList<Integer> maxInWindows3(int [] num, int size) {
		if (size <= 0 || size > num.length)
			return new ArrayList<>(0);
		LinkedList<Integer> queue = new LinkedList<>();
		int[] ret = new int[num.length- size + 1];
		for (int i = num.length - 1; i >= 0; i--) {
			while (!queue.isEmpty() && num[queue.peekLast()] <= num[i])
				queue.pollLast();
			queue.addLast(i);
			if (i + size <= queue.peekFirst())
				queue.pollFirst();
			if (i < num.length - size + 1)
				ret[i] = num[queue.peekFirst()];
		}
			
		return IntStream.of(ret)
				.boxed()
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static void main(String[] args) {
		new MaxInWindows().maxInWindows3(new int[]{2,3,4,2,6,2,5,1}, 3).stream().forEach(System.out::println);
	}

}
