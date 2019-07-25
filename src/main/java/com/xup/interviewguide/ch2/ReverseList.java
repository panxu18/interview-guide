package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.FindKthToTail.DeListNode;
import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

public class ReverseList {

	public ListNode reverseList(ListNode head) {
		ListNode s = new ListNode(0);
		ListNode next = null;
		while (head != null) {
			next = head.next;
			head.next = s.next;
			s.next = head;
			head = next;
		}
		return s.next;
	}
	
	public DeListNode reverseDeList(DeListNode head) {
		DeListNode s = new DeListNode(0);
		DeListNode next = null;
		while (head != null) {
			next = head.next;
			head.next = s.next;
			head.last = s;
			s.next = head;
			if (head.next != null)
				head.next.last = head;
			head = next;
		}
		s.next.last = null;
		return s.next;
	}
}
