package com.xup.interviewguide.ch1;

import java.util.Stack;
import java.util.stream.IntStream;

public class ReverseStackByRecurency {
	
	/*
	 * 1、获取栈底元素
	 * 2、其他元素逆序
	 * 3、将栈底元素放到栈顶
	 */
	public <T> void reverse(Stack<T> stack) {
		if (!stack.isEmpty()) {
			T numBottom = removeBottom(stack);
			reverse(stack);
			stack.push(numBottom);
		}
	}
	
	/*
	 * 返回栈底元素并移除
	 */
	public <T> T removeBottom(Stack<T> stack) {
		if (stack.size() > 1) {
			T numCur = stack.pop();
			T result = removeBottom(stack);
			stack.push(numCur);
			return result;
		}
		return stack.pop();
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		IntStream.iterate(1, a->a+1).limit(5).boxed().forEach(e->stack.add(e));
		stack.stream().forEach(System.out::println);
		new ReverseStackByRecurency().reverse(stack);
		stack.stream().forEach(System.out::println);
	}
}
