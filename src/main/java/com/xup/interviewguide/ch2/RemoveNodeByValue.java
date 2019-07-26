package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

public class RemoveNodeByValue {
	
	public ListNode remove(ListNode head, int k) {
		ListNode s = new ListNode(0);
		ListNode pre = s;
		pre.next = head;
		head = s;
		while ((head = head.next) != null) {
			if (head.val == k)
				pre.next = head.next;
			else
				pre = head;
		}
		return s.next;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,1,2,1};
		ListNode head = ListNode.buildList(arr);
		ListNode.print(new RemoveNodeByValue().remove(head, 1));
	}

}
