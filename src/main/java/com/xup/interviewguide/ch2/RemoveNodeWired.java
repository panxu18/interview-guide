package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

public class RemoveNodeWired {
	
	public void removeNodeWired(ListNode node) {
		if (node == null)
			return;
		if (node.next == null)
			throw new RuntimeException("tail node");
		node.val = node.next.val;
		node.next = node.next.next;
	}

}
