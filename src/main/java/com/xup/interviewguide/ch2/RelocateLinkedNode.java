package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

public class RelocateLinkedNode {
	
	public ListNode relocate(ListNode head) {
		int len = 0;
		ListNode p = head;
		while (p != null) {
			len++;
			p = p.next;
		}
		if (len <= 3)
			return head;
		p = head;
		for (int i = 1; i < len / 2; i++)
			p = p.next;
		ListNode rightHead = p.next;
		p.next = null;
		ListNode leftHead = head;
		ListNode s = new ListNode(0);
		ListNode tail = s;
		while (leftHead != null) {
			tail = tail.next = leftHead;
			leftHead = leftHead.next;
			tail = tail.next = rightHead;
			rightHead = rightHead.next;
		}
		tail.next = rightHead;
		return s.next;
		
	}

	public static void main(String[] args) {
		ListNode head = ListNode.buildList(new int[]{1,2,3,4,5,6});
		ListNode.print(new RelocateLinkedNode().relocate(head));
	}
}
