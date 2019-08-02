package com.xup.interviewguide.ch3;

import java.util.Stack;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

public class TraversalTree {
	
	/*
	 * 递归先序遍历
	 */
	public void preOrderRecur(TreeNode root) {
		if (root == null)
			return;
		System.out.println(root.val);
		preOrderRecur(root.left);
		preOrderRecur(root.right);
	}
	
	/*
	 * 递归中序遍历
	 */
	public void inOrderRecur(TreeNode root) {
		if (root == null)
			return;
		preOrderRecur(root.left);
		System.out.println(root.val);
		preOrderRecur(root.right);
	}
	
	/*
	 * 递归后续遍历
	 */
	public void postOrderRecur(TreeNode root) {
		if (root == null)
			return;
		preOrderRecur(root.left);
		preOrderRecur(root.right);
		System.out.println(root.val);
	}
	
	/*
	 * 非递归先序遍历
	 */
	public void preOrderUnRecur(TreeNode root) {
		if (root == null)
			return;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode p = null;
		while (!stack.isEmpty()) {
			p = stack.pop();
			if (p != null) {
				System.out.println(p.val);
				if (p.right != null)
					stack.push(p.right);
				if (p.left != null)
					stack.push(p.left);
			}
		}
	}
	
	/*
	 * 非递归中序遍历
	 */
	public void inOrderUnRecur(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		while (!stack.isEmpty() || root != null) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			if (!stack.isEmpty()) {
				root = stack.pop();
				System.out.println(root.val);
				root = root.right;
			}
		}
	}
	
	/*
	 * 非递归后续遍历
	 * 从根节点开始执行以下步骤
	 * 1、当前节点左子树不为空且前驱节点不是当前节点的子节点，遍历左子树
	 * 2、当前节点左子树为空时，右子树不为空且前驱节点不是当前右节点，则遍历右子树
	 * 3、当前节点右子树为空或者前驱节点是当前节点的右节点，访问当前节点，返回到父节点
	 */
	public void postOrderUnRecur(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		if (root == null) 
			return;
		TreeNode pre = null;
		stack.push(root);
		while (!stack.isEmpty()) {
			root = stack.peek();
			if (root.left != null && pre != root.left && pre != root.right) 
				stack.push(root.left);
			else if (root.right != null && pre != root.right)
				stack.push(root.right);
			else {
				pre = stack.pop();
				System.out.println(pre);
			}
		}
//		while (!stack.isEmpty()) {
//			root = stack.peek();
//			if ((root.left == null && root.right == null)
//					|| (pre == root.left && root.right == null)
//					|| pre == root.right) {
//				pre = stack.pop();
//				System.out.println(pre);
//			} else if (root.left != null && pre != root.left) 
//				stack.push(root.left);
//			else
//				stack.push(root.right);
//				
//		}
	}

}
