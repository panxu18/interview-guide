package com.xup.interviewguide.ch3;

import java.util.LinkedList;
import java.util.Scanner;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;
import com.xup.interviewguide.ch3.PrintTree.NodeType;

public class FullBSTree {
	private static TreeNode buildTree(Scanner input) {
		if (input.hasNext()) {
			TreeNode root = new TreeNode(input.nextInt());
			int valLeft = input.nextInt();
			int valRight = input.nextInt();
			if (valLeft != 0) {
				root.left = buildTree(input);
			}
			if (valRight != 0) {
				root.right = buildTree(input);
			}
			return root;
		}
		return null;
	}

	/*
	 * 判断以root为根节点的二叉树是否为搜索二叉树
	 */
	private static boolean isBSTree(TreeNode root, TreeNode pre) {
		if (root == null)
			return true;
		return isBSTree(root.left, pre)
				&& (pre == null || pre.val < root.val)
				&& isBSTree(root.right, root);
	}

	/*
	 * 层次遍历所有节点，如果某个节点为空节点，那么队列中所有节点都应该是空节点
	 */
	private static boolean isFullTree(TreeNode root) {
		if (root != null) {
			LinkedList<TreeNode> queue = new LinkedList<>();
			queue.addLast(root);
			while (!queue.isEmpty()) {
				TreeNode cur = queue.pollFirst();
				if (cur == null) {
					while (!queue.isEmpty() && queue.peekFirst() == null)
						queue.pollFirst();
					return queue.isEmpty();
				}
				queue.add(cur.left);
				queue.add(cur.right);
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while (input.hasNext()) {
			input.nextLine();
			TreeNode root = buildTree(input);
			//			new PrintTree().print(root, 1, NodeType.ROOT);
			System.out.println(isBSTree(root, null));
			System.out.println(isFullTree(root));
		}
	}
}
