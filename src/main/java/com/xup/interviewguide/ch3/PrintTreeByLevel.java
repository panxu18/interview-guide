package com.xup.interviewguide.ch3;

import java.util.LinkedList;
import java.util.Scanner;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

public class PrintTreeByLevel {
	
	public void printByLevel(TreeNode root) {
		if (root == null)
			return;
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.addLast(root);
		int level = 0;
		while (!queue.isEmpty()) {
			level++;
			System.out.print("Level " + level + ":");
			int len = queue.size();
			TreeNode p = null;
			while (len-- > 0) {
				p = queue.pollFirst();
				System.out.print(" " + p.val);
				if (p.left != null)
					queue.addLast(p.left);
				if (p.right != null)
					queue.addLast(p.right);
			}
			System.out.print("\n");
		}
	}
	
	public void printByZigZag(TreeNode root) {
		if (root == null)
			return;
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.addLast(root);
		int level = 0;
		while (!queue.isEmpty()) {
			level++;
			if (level % 2 == 1)
				System.out.print("Level " + level + " from left to right :");
			else
				System.out.print("Level " + level + " from right to left :");
			int len = queue.size();
			TreeNode p = null;
			while (len-- > 0) {
				if (level % 2 == 1) {
					p = queue.pollFirst();
					System.out.print(" " + p.val);
					if (p.left != null)
						queue.addLast(p.left);
					if (p.right != null)
						queue.addLast(p.right);
				} else {
					p = queue.pollLast();
					System.out.print(" " + p.val);
					if (p.right != null)
						queue.addFirst(p.right);
					if (p.left != null)
						queue.addFirst(p.left);
				}
			}
			System.out.print("\n");
		}
	}
	
	public static void main(String[] args) {
		TreeNode root = TreeNode.buildTree();
		if (root == null)
			return;
		new PrintTreeByLevel().printByLevel(root);
		new PrintTreeByLevel().printByZigZag(root);
	}

}
