package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

public class SortLinkedList {

	public ListNode sort(ListNode head) {
		if (head == null || head.next == null) 
			return head;
		ListNode pre = head;
		while (pre.next != null) {
			while (pre.next != null && pre.next.val >= pre.val)
				pre = pre.next;
			ListNode nodeMin = getMinNode(pre);
			head = insertNode(head, nodeMin);
		}
		return head;
	}
	
	/*
	 * head 为头结点
	 */
	private ListNode getMinNode(ListNode head) {
		if (head == null || head.next == null)
			return null;
		ListNode pre = head;
		ListNode preMin = head;
		int min = Integer.MAX_VALUE;
		while (pre.next != null) {
			if (pre.next.val < min) {
				min = pre.next.val;
				preMin = pre;
			}
			pre = pre.next;
		}
		ListNode result = preMin.next;
		preMin.next = result.next;
		return result;
	}
	
	/*
	 * 向有序链表中插入节点
	 */
	private ListNode insertNode(ListNode head, ListNode e) {
		if (e == null)
			return head;
		if (head.val > e.val) {
			e.next = head;
			return e;
		}
		ListNode pre = head;
		while (pre.next != null && pre.next.val <= e.val)
			pre = pre.next;
		e.next = pre.next;
		pre.next = e;
		return head;
	}
	
	public static void main(String[] args) {
		ListNode head = ListNode.buildList(new int[]{1});
		ListNode.print(new SortLinkedList().sort(head));
	}
}
