package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.PrintCommonInList.ListNode;

public class ListPartition {

	public ListNode listPartition(ListNode head, int pivot) {
		if (head == null || head.next == null)
			return head;
		ListNode headLeft = new ListNode(0);
		ListNode tailLeft = headLeft;
		ListNode headRight =  new ListNode(0);
		ListNode tailRight = headRight;
		ListNode headMid =  new ListNode(0);
		ListNode tailMid = headMid;
		ListNode headNew = new ListNode(0);
		ListNode tailNew = headNew;
		while (head != null) {
			if (head.val < pivot)
				tailLeft = tailLeft.next = head;
			else if (head.val > pivot)
				tailRight = tailRight.next = head;
			else
				tailMid = tailMid.next = head;
			head = head.next;
		}
		if ((tailNew.next = headLeft.next) != null)
			tailNew = tailLeft;
		if ((tailNew.next = headMid.next) != null)
			tailNew = tailMid;
		if ((tailNew.next = headRight.next) != null)
			tailNew = tailRight;
		tailNew.next = null;
		return headNew.next;
	}
	
	public static void main(String[] args) {
		ListNode head = null;
		ListNode tail = null;
		int[] arr = {9,0,4,5,1,3};
		for (int v : arr) {
			if (head == null) {
				head = new ListNode(v);
				tail = head;
			} else {
				tail.next = new ListNode(v);
				tail = tail.next;
			}
		}
		head = new ListPartition().listPartition(head, 3);
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
		
	}
}
