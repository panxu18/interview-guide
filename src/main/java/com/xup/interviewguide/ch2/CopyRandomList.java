package com.xup.interviewguide.ch2;

public class CopyRandomList {

	public static class RandomListNode {
	    int label;
	    RandomListNode next = null;
	    RandomListNode random = null;

	    RandomListNode(int label) {
	        this.label = label;
	    }
	}
	
	public RandomListNode copyRandomList(RandomListNode pHead) {
		if (pHead == null)
			return null;
		RandomListNode headNew = pHead;
		RandomListNode nodeNew = null;
		// 原地复制节点
		while (headNew != null) {
			nodeNew = new RandomListNode(headNew.label);
			nodeNew.next = headNew.next;
			headNew.next = nodeNew;
			headNew = nodeNew.next;
		}
		// 复制随机引用
		headNew = pHead;
		while (headNew != null) {
			nodeNew = headNew.next;
			if (headNew.random != null)
				nodeNew.random = headNew.random.next;
			headNew = nodeNew.next;
		}
		// 拆分链表
		nodeNew = headNew = null;
		while (pHead != null) {
			if (headNew == null)
				nodeNew = headNew = pHead.next;
			else
				nodeNew = nodeNew.next = pHead.next;
			pHead = pHead.next = nodeNew.next;
		}
		return headNew;
    }
}
