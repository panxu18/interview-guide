package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

public class FindKthToTail {
	
	public static class DeListNode {
	    int val;
	    DeListNode last;
	    DeListNode next;

	    DeListNode(int val) {
	        this.val = val;
	    }
	}
	
	public ListNode findKthToTailInList(ListNode head,int k) {
		ListNode p = head;
		ListNode q = head;
		ListNode pre = null;
		for (int i = 0; i < k; i++) {
			if (p == null)
				return head;
			p = p.next;
		}
		while (p != null) {
			pre = q;
			q = q.next;
			p = p.next;
		}
		if (pre == null)
			head = head.next;
		else
			pre.next = q.next;
		return head;
			
	}
	
	public DeListNode findKthToTailInDeList(DeListNode head,int k) {
		DeListNode p = head;
		DeListNode q = head;
		for (int i = 0; i < k; i++) {
			if (p == null)
				return null;
			p = p.next;
		}
		while (p != null) {
			q = q.next;
			p = p.next;
		}
		if (q == head) {
			head = head.next;
			head.last = null;
			head.last.next = null;
		}
		else {
			q.last.next = q.next;
			if (q.next !=null)
				q.next.last = q.last;
		}
		return head;
	}

}
