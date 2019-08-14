package com.xup.interviewguide.ch3;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

public class BalancedTree {
	
	public boolean isBalanced(TreeNode root) {
		return isBalencedCore(root) >= 0;
	}

	private int isBalencedCore(TreeNode root) {
		if (root == null)
			return 0;
		int heightLeft = -1;
		int heightRight = -1;
		if ((heightLeft = isBalencedCore(root.left)) >= 0
				&& (heightRight = isBalencedCore(root.right)) >= 0
				&& Math.abs(heightLeft - heightRight) < 2)
			return Math.max(heightLeft, heightRight) + 1;
		return -1;
	}

}
