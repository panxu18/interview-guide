package com.xup.interviewguide.ch2;

public class PrintCommonInList {
	
	public static class ListNode {
	    int val;
	    ListNode next = null;

	    ListNode(int val) {
	        this.val = val;
	    }
	}
	
	public void pritCommonPart(ListNode head1, ListNode head2) {
		while (head1 != null && head2 != null) {
			if (head1.val < head2.val)
				head1 = head1.next;
			else if (head1.val > head2.val)
				head2 = head2.next;
			else {
				System.out.println(head1.val);
				head1 = head1.next;
				head2 = head2.next;
			}
		}
	}

}
