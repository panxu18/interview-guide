package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

public class InsertNodeInLinkedList {
	
	public ListNode insertNode(ListNode head, int e) {
		ListNode node = new ListNode(e);
		if (head == null)
			return node;
		if (head.next == head) {
			node.next = head.next;
			head.next = node;
			if (e < head.val)
				return node;
			else
				return head;
		}
		ListNode pre = head;
		while (pre.next != head && pre.next.val <= e)
			pre = pre.next;
		node.next = pre.next;
		pre.next = node;
		return head;
	}

	public static void main(String[] args) {
		ListNode head = ListNode.buildList(new int[]{1,5,6,7});
		ListNode tail = ListNode.getTailNode(head);
		if (tail != null)
			tail.next = head;
		ListNode.printCircle(new InsertNodeInLinkedList().insertNode(head, 4));
	}
}
