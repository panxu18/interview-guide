package com.xup.interviewguide.ch2;

import com.xup.interviewguide.ch2.FindKthToTail.DeListNode;

public class TransferTree {

	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;

	    }
	}
	TreeNode head = new TreeNode(-1);
	TreeNode tail = head;
	
	/*
	 * 利用中序遍历构建链表
	 */
	public void convertCore(TreeNode root) {
		if (root == null)
			return;
		convertCore(root.left);
		root.left = tail;
		tail.right = root;
		tail = root;
		
	}
	
}
