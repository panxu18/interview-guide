package com.xup.interviewguide.ch3;

import java.util.Scanner;
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
	public static void preOrderUnRecur(TreeNode root) {
		if (root == null)
			return;
		StringBuffer sb = new StringBuffer();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode p = null;
		stack.push(root);
		while (!stack.isEmpty()) {
			p = stack.pop();
			if (p != null) {
				sb.append(" " + p.val);
				if (p.right != null)
					stack.push(p.right);
				if (p.left != null)
					stack.push(p.left);
			}
		}
		System.out.println(sb.deleteCharAt(0));
	}

	/*
	 * 非递归中序遍历
	 */
	public static void inOrderUnRecur(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		StringBuffer sb = new StringBuffer();
		while (!stack.isEmpty() || root != null) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			if (!stack.isEmpty()) {
				root = stack.pop();
				sb.append(" " + root.val);
				root = root.right;
			}
		}
		System.out.println(sb.deleteCharAt(0));
	}

	/*
	 * 非递归后续遍历
	 * 从根节点开始执行以下步骤
	 * 1、当前节点不为空，遍历左子树
	 * 2、当前节点为空，栈顶节点没有右子树，或者右子树已经遍历，则访问当前节点
	 * 3、否则遍历右子树
	 */
	public static void postOrderUnRecur(TreeNode root) {
		if (root == null)
			return;
		Stack<TreeNode> stack = new Stack<>();
		StringBuffer sb = new StringBuffer();
		TreeNode pre = null;
		TreeNode cur = root;
		while (!stack.isEmpty() || cur != null) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else if (stack.peek().right == null 
					|| pre == stack.peek().right){
				sb.append(" " + stack.peek().val);
				pre = stack.pop();
			} else 
				cur = stack.peek().right;
		}
		System.out.println(sb.deleteCharAt(0));
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		TreeNode root = TreeNode.buildTree(input);
		preOrderUnRecur(root);
		inOrderUnRecur(root);
		postOrderUnRecur(root);
	}

}
