package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

public class ReversePartList {
	
	public ListNode reversePart(ListNode head, int from, int to) {
		if (from > to || from < 1 ||
				head == null || head.next == null)
			return head;
		ListNode pre = null;
		ListNode last = null;
		ListNode p = head;
		int count = 0;
		while (p != null) {
			count++;
			if (count == from - 1)
				pre = p;
			if (count == to + 1)
				last = p;
			p = p.next;
		}
		if (to > count)
			return head;
		if (pre != null)
			p = pre.next;
		else 
			p = head;
		ListNode s = new ListNode(0);
		s.next = last;
		ListNode next;
		while (p != null) {
			next = p.next;
			p.next = s.next;
			s.next = p;
			p = next;
		}
		if (pre != null)
			pre.next = s.next;
		else
			head = s.next;
		return head;
	}

}
