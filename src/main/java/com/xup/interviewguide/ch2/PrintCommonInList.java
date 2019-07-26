package com.xup.interviewguide.ch2;

public class PrintCommonInList {

	public static class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}

		public static ListNode buildList(int[] arr) {
			if (arr == null)
				return null;
			ListNode head = new ListNode(0);
			ListNode tail = head;
			for (int v : arr)
				tail = tail.next = new ListNode(v);
			return head.next;
		}
		
		public static void print(ListNode node) {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			while (node != null) {
				sb.append(node.val);
				sb.append(",");
				node = node.next;
			}
			if (sb.charAt(sb.length() - 1) == ',')
				sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			System.out.println(sb);
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
