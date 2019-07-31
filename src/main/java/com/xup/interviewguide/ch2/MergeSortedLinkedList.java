package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

public class MergeSortedLinkedList {
	
	public ListNode merge(ListNode head1, ListNode head2) {
		ListNode s = new ListNode(0);
		ListNode tail = s;
		
		while (head1 != null && head2 != null) {
			if (head1.val <= head2.val) {
				tail = tail.next = head1;
				head1 = head1.next;
			} else {
				tail = tail.next = head2;
				head2 = head2.next;
			}
		}
		while (head1 != null) {
			tail = tail.next = head1;
			head1 = head1.next;
		}
		while (head2 != null) {
			tail = tail.next = head2;
			head2 = head2.next;
		}
		tail.next = null;
		return s.next;
	}
	
	public static void main(String[] args) {
		ListNode head1 = ListNode.buildList(new int[]{});
		ListNode head2 = ListNode.buildList(new int[]{});
		ListNode.print(new MergeSortedLinkedList().merge(head1, head2));
	}
}
