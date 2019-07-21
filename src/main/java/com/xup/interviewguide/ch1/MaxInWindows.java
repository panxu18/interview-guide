package com.xup.interviewguide.ch1;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.sun.glass.ui.TouchInputSupport.TouchCountListener;

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
		for (int i = num.length; i >= 0; i--)
			for (int j = 0; j < size && i + j < num.length; j++) // 向后比较并更新候选值
				if (num[i] > num[i + j])
					num[i + j] = num[i];
		return IntStream.of(num)
						.skip(size - 1)
						.boxed()
						.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static void main(String[] args) {
		new MaxInWindows().maxInWindows(new int[]{2,3,4,2,6,2,5,1}, 3).stream().forEach(System.out::println);
	}

}
