package com.xup.interviewguide.ch3;

import java.util.Stack;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

public class MaxSearchTopo {

	//最大搜索二叉树根节点
	private TreeNode MAX_SEARCH_TREE;
	//最大搜索二叉大小
	private int MAX_SEARCH_TREE_SIZE;

	/*
	 * 遍历二叉树，对每一个节点，搜索以该节点为根的最大符合二叉
	 * 搜索树结构的拓扑结构。
	 * 时间复杂度为O(n^2),空间复杂度为O(h)
	 */
	public int getTopoSize1(TreeNode root) {
		if (root == null)
			return 0;
		return Math.max(getTopoSizeOfNodeRecur(root, 
				Integer.MIN_VALUE, Integer.MAX_VALUE), 
				Math.max(getTopoSize1(root.left), 
						getTopoSize1(root.right)));
	}

	/*
	 * 递归搜索从根节点开始的最大符合二叉搜索结构的拓扑结构
	 * 因为每个节点都有一个范围，这个范围由已经遍历过的祖先节点决定，
	 * 如果节点在范围内，则应该是拓扑结构中的一个元素。
	 */
	public int getTopoSizeOfNodeRecur(TreeNode root, int min, int max) {
		if (root == null || root.val < min || root.val > max)
			return 0;
		return 1 + getTopoSizeOfNodeRecur(root.left, min, root.val) +
				getTopoSizeOfNodeRecur(root.right, root.val, max);
	}

	/*
	 * 利用中序遍历非递归解法搜索根节点的符合二叉搜索树的最大拓扑结构
	 * 因为二叉搜索树的中序遍历序列是递增序列，当一个节点入栈时，其值
	 * 一定小于栈顶节点的值。当一个节点出栈后，后续节点应该都比这个节点
	 * 的值大，所以应该更新最小范围为当前出栈元素的值
	 */
	public int getTopoSizeOfNodeUnRecur(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		int min = Integer.MIN_VALUE;
		int count = 0;
		TreeNode p = root;
		while (!stack.isEmpty() || p != null) {
			while (p != null && p.val >= min 
					&& (stack.isEmpty() || p.val <= stack.peek().val)) {
				stack.push(p);
				p = p.left;
			}
			p = null;
			if (!stack.isEmpty()) {
				count++;
				min = stack.peek().val;
				p = stack.pop().right;
			}
		}
		return count;
	}
	
	public int getTopoSize2(TreeNode root) {
		MAX_SEARCH_TREE_SIZE = 0;
		getTopoSizeOfNodeRecur(root, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
		return MAX_SEARCH_TREE_SIZE;
	}
	
	/*
	 * 最大拓扑结构有三种可能，
	 * 1、左子树中最大的拓扑结构
	 * 2、右子树中最大的拓扑结构
	 * 3、根节点的拓扑结构
	 * 为了求上面3种情况，需要递归求出所有节点的拓扑结构，然后得到其中的最大值。
	 * 由于拓扑结构有重合，会产生很多重复的搜索。
	 * 拓扑结构重合意思是根节点的左节点的拓扑结构和根节点的拓扑结构有一部分
	 * 重合。 通过观察可以发现根节点的左节点的拓扑结构和 根节点的拓扑结构不重合的
	 * 部分只能出现在左子树的右边界上，也就是从左子树的右边界的某个节点开始，
	 * 该节点以及其后裔不属于根节点的拓扑结构。
	 * 因此搜索出根节点的拓扑结构后，左节点的拓扑结构就得到了一部分，现在只需要
	 * 搜索剩下不重合的部分，就能得到左节点的完整拓扑结构。
	 * 根节点的有节点也有相同情况。
	 * 因此通过如下步骤就可以不重复的搜索所有节点的拓扑结构
	 * 1、搜索根节点的拓扑结构
	 * 2、搜索左子树最右边一部分子树（不包含在根节点拓扑结构中）
	 * 3、搜索右子树最左边一部分子树（不包含在根节点拓扑结构中）
	 */
	public int getTopoSizeOfNodeRecur(TreeNode root, int offset, int min, int max) {
		if (root == null || root.val < min || root.val > max)
			return 0;
		int total = 0;
		int leftCount = getTopoSizeOfNodeRecur(root.left, 0, min, root.val);
		int rightCount = getTopoSizeOfNodeRecur(root.right, 0, root.val, max);
		total = 1 + leftCount + rightCount + offset;
		MAX_SEARCH_TREE_SIZE = Math.max(total, MAX_SEARCH_TREE_SIZE);
		TreeNode p = root.left;
		while (p != null && p.val >= min && p.val <= root.val) {
			min = p.val;
			p = p.right;
		}
		if (p != null) {
			if (p.val < min) {
				MAX_SEARCH_TREE_SIZE = Math.max(MAX_SEARCH_TREE_SIZE, 
						getTopoSizeOfNodeRecur(p, 0, Integer.MIN_VALUE, Integer.MAX_VALUE));
			} else {
				MAX_SEARCH_TREE_SIZE = Math.max(MAX_SEARCH_TREE_SIZE, 
						getTopoSizeOfNodeRecur(p, leftCount, min, Integer.MAX_VALUE));
			}
			
		}
		
		p = root.right;
		while (p != null && p.val >= root.val && p.val <= max) {
			max = p.val;
			p = p.left;
		}
		if (p != null) {
			if (p.val > max)
				MAX_SEARCH_TREE_SIZE = Math.max(MAX_SEARCH_TREE_SIZE, 
						getTopoSizeOfNodeRecur(p, 0, Integer.MIN_VALUE, Integer.MAX_VALUE));
			else
				MAX_SEARCH_TREE_SIZE = Math.max(MAX_SEARCH_TREE_SIZE, 
						getTopoSizeOfNodeRecur(p, rightCount, Integer.MIN_VALUE, max));
		}
		
		return total;
				
	}
	
	/*
	 * 查找搜索二叉树最大节点
	 */
	private TreeNode getMaxNode(TreeNode root) {
		assert root != null;
		while (root.right != null)
			root = root.right;
		return root;
	}
	
	/*
	 * 查找搜索二叉树最小节点
	 */
	private TreeNode getMinNode(TreeNode root) {
		assert root != null;
		while (root.left != null)
			root = root.left;
		return root;
	}


	

	public static void main(String[] args) throws Throwable {
		TreeNode root = new SerializeTree().deserialize("6,1,0,#,#,3,#,#,12,10,4,2,#,#,"
				+ "5,#,#,14,11,#,#,15,#,#,13,20,#,#,16,#,#,");
		System.out.println(new MaxSearchTopo().getTopoSize2(root));
	}

}
