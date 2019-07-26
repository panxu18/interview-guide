package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

public class ReverseListByK {
	
	public ListNode reverseKNode (ListNode head, int k) {
		if (head == null || k <= 1)
			return head;
		ListNode s = new ListNode(0);
		ListNode tail = s;
		ListNode nextHead = null;
		ListNode node = null; 
		while (head != null) {
			int count = 1;
			node = head;
			while (count++ < k && (node = node.next) != null);
			if (count < k || node == null)
				break;
			nextHead = node.next;
			node.next = null;
			tail.next = reverse(head);
			tail = head;
			tail.next = nextHead;
			head = nextHead;
		}
		return s.next;
	}
	
	public ListNode reverse(ListNode head) {
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
		ListNode head = ListNode.buildList(new int[] {1,2,3,4,5,6,7,8});
		ListNode.print(new ReverseListByK().reverseKNode(head, 3));
	}

}
