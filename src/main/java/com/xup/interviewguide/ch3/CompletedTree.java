package com.xup.interviewguide.ch3;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

public class CompletedTree {
	
	public static int getNodeNums(TreeNode root) {
		if (root == null)
			return 0;
		int leftNums = getDepth(root.left);
		int rightNums = getDepth(root.right);
		if (leftNums < 0)
			return 1 + rightNums + getNodeNums(root.left);
		if (rightNums < 0)
			return 1 + leftNums + getNodeNums(root.left);
		return 1 + leftNums + rightNums;
	}
	
	/*
	 * 计算满二叉树的高度,如果不是满二叉树返回-1
	 */
	private static int getDepth(TreeNode root) {
		if (root == null)
			return 0;
		int d = 0;
		TreeNode lc = root;
		TreeNode rc = root;
		while (lc != null && rc != null) {
			d++;
			lc = lc.left;
			rc = rc.right;
		}
		if (lc != rc)
			return -1;
		else
			return 1 << d - 1;
	}
	
}
