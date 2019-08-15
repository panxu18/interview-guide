package com.xup.interviewguide.ch3;

import java.util.Scanner;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

public class MaxDistanceInTree {

	static int MAX_DISTANCE = 0;
	
	public static int depth(TreeNode root) {
		if (root == null)
			return 0;
		int ld = depth(root.left);
		int rd = depth(root.right);
		int d = Math.max(ld, rd) + 1;
		MAX_DISTANCE = Math.max(MAX_DISTANCE, ld + rd + 1);
		return d;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		TreeNode root = TreeNode.buildTree(input);
		MAX_DISTANCE = 0;
		depth(root);
		System.out.println(MAX_DISTANCE);
	}
}
