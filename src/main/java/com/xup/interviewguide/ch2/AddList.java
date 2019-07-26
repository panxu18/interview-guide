package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

public class AddList {
	
	public ListNode addList(ListNode head1, ListNode head2) {
		head1 = reverse(head1);
		head2 = reverse(head2);
		ListNode headNew = new ListNode(0);
		ListNode tail = headNew;
		int c = 0; // 进位
		while (head1 != null && head2 != null) {
			int v1 = head1.val + head2.val + c;
			tail = tail.next = new ListNode(v1 % 10);
			c = v1 / 10;
			head1 = head1.next;
			head2 = head2.next;
		}
		while (head1 != null) {
			int v1 = head1.val + c;
			tail = tail.next = new ListNode(v1 % 10);
			c = v1 / 10;
			head1 = head1.next;
		}
		while (head2 != null) {
			int v1 = head2.val + c;
			tail = tail.next = new ListNode(v1 % 10);
			c = v1 / 10;
			head2 = head2.next;
		}
		if (c != 0) {
			tail = tail.next = new ListNode(c);
		}
		head1 = reverse(head1);
		head2 = reverse(head2);
		return reverse(headNew.next);
	}
	
	private ListNode reverse(ListNode head) {
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
	
	public static void main(String[] args) {
		ListNode head1 = ListNode.buildList(new int[] {2});
		ListNode head2 = ListNode.buildList(new int[] {});
		ListNode.print(new AddList().addList(head1, head2));
	}
}
