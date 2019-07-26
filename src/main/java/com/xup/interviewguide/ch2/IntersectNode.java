package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

public class IntersectNode {
	
	public ListNode getIntersectNode (ListNode head1, ListNode head2) {
		if (head1 == null || head2 == null)
			return null;
		ListNode nodeLoop1 = getLoopNode(head1); 
		ListNode nodeLoop2 = getLoopNode(head2); 
		if (nodeLoop1 == null && nodeLoop2 == null) {
			return getIntersectNodeWithoutLoop(head1, head2);
		} else if (nodeLoop1 != null && nodeLoop2 != null) {
			return getIntersectNodeWithLoop(head1, nodeLoop1, head2, nodeLoop2);
		} else
			return null;
	}
	
	/*
	 * 带环链表第一公共节点
	 */
	private ListNode getIntersectNodeWithLoop (ListNode head1, ListNode nodeLoop1,
			ListNode head2, ListNode nodeLoop2) {
		if (nodeLoop1 == nodeLoop2) {
			ListNode result = null;
			ListNode next = nodeLoop1.next;
			nodeLoop1.next = null;
			result = getIntersectNodeWithoutLoop(head1, head2);
			nodeLoop1.next = next;
			return result;
		} else {
			ListNode nodeCur = nodeLoop1;
			while (nodeCur != nodeLoop1 && nodeCur != nodeLoop2)
				nodeCur = nodeCur.next;
			if (nodeCur == nodeLoop2)
				return nodeCur;
			else
				return null;
		}
	}
	
	/*
	 * 无环链表找第一个公共节点
	 */
	private ListNode getIntersectNodeWithoutLoop (ListNode head1, ListNode head2) {
		if (head1 == null || head2 == null)
			return null;
		int len1 = 1;
		int len2 = 1;
		ListNode node1 = head1;
		ListNode node2 = head2;
		while ((node1 = node1.next) != null)
			len1++;
		while ((node2 = node2.next) != null)
			len2++;
		int offset = 0;
		if (len1 >= len2) {
			offset = len1 - len2;
			node1 = head1;
			node2 = head2;
		} else {
			offset = len2 - len1;
			node1 = head2;
			node2 = head1;
		}
		while (offset-- > 0) {
			node1 = node1.next;
		}
		while (node1 != null) {
			if (node1.val == node2.val)
				return node1;
			node1 = node1.next;
			node2 = node2.next;
		}
		return node1;
	}
	
	/*
	 * 链表环的入口节点
	 */
	private ListNode getLoopNode(ListNode head) {
		if (head == null)
			return null;
		ListNode p = head.next;
		ListNode q = head;
		while (p != null && p != q) {
			p = p.next;
			if (p == null)
				break;
			p = p.next;
			q = q.next;
		}
		if (p == null)
			return null;
		int lenLoop = 1;
		while ((p = p.next) != q)
			lenLoop++;
		q = p = head;
		while (lenLoop-- > 0) {
			p = p.next;
		}
		while (p != q) {
			p = p.next;
			q = q.next;
		}
		return p;
	}

}
