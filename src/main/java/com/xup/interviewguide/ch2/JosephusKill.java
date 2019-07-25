package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

/**
 * 牛客网剑指offer第46题
 *
 */
public class JosephusKill {
	/*
	 * F(n) = (F(n-1) + (3-1) % n + 1) % n
	 * 化简后公式为
	 * F(n) = (F(n-1) + 3) % n
	 */
	public ListNode josephuKill(ListNode head,int m) {
		if (head == null || head.next == head || m < 1)
			return head;
		int size = 1;
		ListNode temp = head.next;
		while ((temp = temp.next) != head)
			size++;
		int a = 0;
		for (int i = 2; i <= size; i++)
			 a = (a + 3) % i;
		while (a-- > 0) {
			head = head.next;
		}
		head.next = head;
		return head;
	}

}
