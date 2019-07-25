package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

public class PalindromeList {
	
	public boolean chkPalindrome(ListNode head) {
		if (head == null || head.next == null)
			return true;
		
		ListNode mid = head;// 中间节点
		ListNode tail = head.next.next;
		while (tail != null) {
			mid = mid.next;
			if (tail.next != null)
				tail = tail.next.next;
			else
				break;
		}
		ListNode head2 = mid.next;
		ListNode next = null;
		mid.next = null;
		//反转链表
		while (head2 != null) {
			next = head2.next;
			head2.next = mid.next;
			mid.next = head2;
			head2 = next;
		}
		// 判断回文结构
		head2 = mid.next;
		while (head2 != null) {
			if (head.val != head2.val)
				return false;
			head = head.next;
			head2 = head2.next;
		}
		head2 = mid.next;
		next = null;
		mid.next = null;
		//反转链表
		while (head2 != null) {
			next = head2.next;
			head2.next = mid.next;
			mid.next = head2;
			head2 = next;
		}
		return true;
    }
	
	public static void main(String[] args) {
		ListNode head = null;
		ListNode tail = null;
		int[] arr = {559,526,134,568,197,532};
		for (int v : arr) {
			if (head == null) {
				head = new ListNode(v);
				tail = head;
			} else {
				tail.next = new ListNode(v);
				tail = tail.next;
			}
		}
		System.out.println(new PalindromeList().chkPalindrome(head));
	}
}
