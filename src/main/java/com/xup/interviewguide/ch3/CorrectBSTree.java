package com.xup.interviewguide.ch3;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

public class CorrectBSTree {
	
	/*
	 * BS树的中序遍历序列为升序，如果遇到降序则代表该节点有问题，
	 * 因为交换节点在序列上可能相邻或者不相邻，所以分别会产生1次
	 * 降序和两次降序
	 */
	public ArrayList<Integer> findErrorNode(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<>(2);
		findErrorNodeValueCore(root, null, result);
		return result;
	}

	private void findErrorNodeValueCore(TreeNode root, TreeNode pre, ArrayList<Integer> result) {
		if (root != null) {
			findErrorNodeValueCore(root.left, pre, result);
			if (pre != null && pre.val > root.val) {
				if (result.isEmpty()) {
					result.add(root.val);
					result.add(pre.val);
				} else {
					result.set(0, root.val);
				}
			}
			findErrorNodeValueCore(root.right, root, result);
		}
	}
	
	/*
	 * 查找错误的节点
	 */
	private void findErrorNodeCore(TreeNode root, TreeNode pre, ArrayList<TreeNode> result) {
		if (root != null) {
			findErrorNodeCore(root.left, pre, result);
			if (pre != null && pre.val > root.val) {
				if (result.isEmpty()) {
					result.add(root);
					result.add(pre);
				} else {
					result.set(0, root);
				}
			}
			findErrorNodeCore(root.left, root, result);
		}
	}
	
	/*
	 * 查找错误节点的父节点
	 */
	private void findErrorParrentNode(TreeNode root, ArrayList<TreeNode> errorNodes, ArrayList<TreeNode> result) {
		if (result.size() < 2 && root != null) {
			if (root.left == errorNodes.get(0) || root.right == errorNodes.get(0)
					|| root.left == errorNodes.get(1) || root.right == errorNodes.get(1)) {
				result.add(root);
			}
			findErrorParrentNode(root.left, errorNodes, result);
			findErrorParrentNode(root.right, errorNodes, result);
		}
	}
	
	/*
	 * 用新节点代替旧节点
	 */
	private void replace(TreeNode parent, TreeNode node, TreeNode newNode) {
		if (parent != null) {
			if (node == parent.left) {
				newNode = parent.left;
			} else {
				newNode = parent.right;
			}
		}
		newNode.left = node.left;
		newNode.right = node.right;
	}
	
	/*
	 * 先用两个空节点代替需要交换的节点，然后再将其交换并替换回去
	 */
	private void correctBSTree(TreeNode root) {
		ArrayList<TreeNode> errorNodes = new ArrayList<>(2);
		findErrorNodeCore(root, null, errorNodes);
		ArrayList<TreeNode> parentNodes = new ArrayList<>(2);
		findErrorParrentNode(root, errorNodes, parentNodes);
		if (parentNodes.size() < 2)
			parentNodes.add(null);
		if (parentNodes.get(0).left != errorNodes.get(0)
				&& parentNodes.get(0).right != errorNodes.get(0)) {
			TreeNode parent1 = parentNodes.get(1);
			parentNodes.set(1, parentNodes.get(0));
			parentNodes.set(0, parent1);
		}
		TreeNode s1 = new TreeNode(0);
		TreeNode s2 = new TreeNode(0);
		replace(parentNodes.get(0), errorNodes.get(0), s1);
		replace(parentNodes.get(1), errorNodes.get(1), s2);
		replace(parentNodes.get(0), s1, errorNodes.get(1));
		replace(parentNodes.get(1), s2, errorNodes.get(0));
		
	}
	

}
