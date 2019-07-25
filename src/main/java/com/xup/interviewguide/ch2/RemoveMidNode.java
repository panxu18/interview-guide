package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

public class RemoveMidNode {

	public ListNode removeMidNode(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode p = head.next.next;
		if (p == null)
			head = head.next;
		ListNode pre = head;
		while (p != null) {
			pre = pre.next;
			if (p.next != null)
				p = p.next.next;
			else
				break;
		}
		pre.next = pre.next.next;
		return head;

	}

	public ListNode removeMidNode2(ListNode head, int a, int b) {
		if (a < 1 || a > b)
			return head;
		int len = 0;
		ListNode p = head;
		while (p != null) {
			len++;
			p = p.next;
		}
		int index = a * len / b;
		if (index == 1)
			head = head.next;
		p = head;
		while (p != null && --index > 1) {
			p = p.next;
		}
		if (p != null) {
			p.next = p.next.next;
		}
		return head;
	}
}
