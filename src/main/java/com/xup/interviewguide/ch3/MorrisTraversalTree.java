package com.xup.interviewguide.ch3;

import java.util.ArrayList;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

/*
 * 节点数为n的二叉树中有n+1个空指针，即可以使用这些空指针指向所有节点，
 * 每个节点的左子树的最右节点的右指针指向指向该节点，这样任意一个节点的
 * 左子树遍历完成后都可以回到该节点进行右子树的遍历。
 */
public class MorrisTraversalTree {
	
	public ArrayList<Integer> preOrderTree(TreeNode root) {
		if (root == null)
			return new ArrayList<>(0);
		ArrayList<Integer> result = new ArrayList<>();
		TreeNode mostRight = null;
		while (root != null) {
			result.add(root.val);
			if (root.left != null) {
				mostRight = root.left;
				while (mostRight.right != null && mostRight.right != root) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = root;
					root = root.left;
					continue;
				} else 
					mostRight.right = null;
			}
			root = root.right;
			
		}
		return result;
	}
	
	public ArrayList<Integer> inOrderTree(TreeNode root) {
		if (root == null)
			return new ArrayList<>(0);
		ArrayList<Integer> result = new ArrayList<>();
		TreeNode mostRight = null;
		while (root != null) {
			if (root.left != null) {
				mostRight = root.left;
				while (mostRight.right != null && mostRight.right != root) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = root;
					root = root.left;
					continue;
				} else 
					mostRight.right = null;
			}
			result.add(root.val);
			root = root.right;
		}
		return result;
	}
	
	public ArrayList<Integer> postOrderTree(TreeNode root) {
		if (root == null)
			return new ArrayList<>(0);
		TreeNode s = root;
		ArrayList<Integer> result = new ArrayList<>();
		TreeNode mostRight = null;
		while (root != null) {
			if (root.left != null) {
				mostRight = root.left;
				while (mostRight.right != null && mostRight.right != root) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = root;
					root = root.left;
					continue;
				} else {
					mostRight.right = null;
					root.left = reverse(root.left);
					mostRight = root.left;
					while (mostRight != null) {
						result.add(mostRight.val);
						mostRight = mostRight.right;
					}
					root.left = reverse(root.left);
				}
			}
			root = root.right;
		}
		root = s = reverse(s);
		while (root != null) {
			result.add(root.val);
			root = root.right;
		}
		reverse(s);
		return result;
	}
	
	private TreeNode reverse(TreeNode head) {
		TreeNode s = new TreeNode(0);
		TreeNode next  = null;
		while (head != null) {
			next = head.right;
			head.right = s.right;
			s.right = head;
			head = next;
		}
		return s.right;
	}
	
	public static void main(String[] args) throws Throwable {
		TreeNode root = new SerializeTree().deserialize("1,2,#,4,7,#,#,8,#,11,13,#,#,14,#,#,"
				+ "3,5,9,12,15,#,#,16,#,#,#,10,#,#,6,#,#,");
		System.out.println(new MorrisTraversalTree().postOrderTree(root));
	}

}
