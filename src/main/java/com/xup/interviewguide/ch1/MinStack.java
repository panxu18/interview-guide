package com.xup.interviewguide.ch1;

import java.util.Stack;

/**
 * 牛客网剑指offer第20题
 */
public class MinStack {
	private Stack<Integer> stackData = new Stack<>();
	private Stack<Integer> stackMin = new Stack<>();
	
	/*
	 * stackMin中保存当前最小元素，如果新加入的元素小于等于最小元素，
	 * 那么同时将其插入到stackMin中
	 */
	public void push(int node) {
		stackData.add(node);
		if (stackMin.isEmpty() || stackMin.peek() >= node)
			stackMin.add(node);
	}
	
	/*
	 * 如果出栈的元素是最小元素则将最小元素冲stackMin中出栈
	 * 
	 */
	public void pop() {
		int val = stackData.pop();
		if (!stackMin.isEmpty() && stackMin.peek() == val)
			stackMin.pop();
	}
	
	public int top() {
		return stackData.peek();
	}
	
	public int min() {
		return stackMin.peek();
	}

}
