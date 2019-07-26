package com.xup.interviewguide.ch2;

import java.util.HashMap;

import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

public class RemoveDuplicteNode {
	
	public ListNode remove(ListNode head) {
		if (head == null || head.next == null)
			return head;
		HashMap<Integer, Boolean> map = new HashMap<>();
		ListNode node = head;
		ListNode nodePre = head;
		map.put(node.val, Boolean.valueOf(true));
		while ((node = node.next) != null) {
			if (map.get(node.val) != null)
				nodePre.next = node.next;
			else {
				nodePre = node;
				map.put(node.val, Boolean.valueOf(true));
			}
		}
		return head;
	}

	public static void main(String[] args) {
		int[] arr = {1,2,3,3,4,4,2,1,1};
		ListNode head = ListNode.buildList(arr);
		ListNode.print(new RemoveDuplicteNode().remove(head));
	}
}
