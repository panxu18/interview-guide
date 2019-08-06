package com.xup.interviewguide.ch3;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

public class MaxSearchTree {
	
	private TreeNode MAX_SEARCH_TREE;
	private int MAX_SEARCH_TREE_SIZE;
	
	
	/*
	 * 题目要求找到最大的二叉搜索子树，补充一下子树的定义，从非根节点节点a开始
	 * a以及a的所有后裔构成一个子树
	 * 
	 * 判断一个二叉树是不是搜索二叉树一般的方法就是利用先序遍历，如果
	 * 先序遍历序列是升序则是搜索二叉树，如果将此方法应用到本题，那么需要
	 * 对遍历所有子树。
	 * 
	 * 判断一个二叉树是不是搜索二叉树第二种方法的步骤如下
	 * 1、先判断其左右子树是否是搜索二叉树，如果不是那么原二叉树不是搜索二叉树
	 * 2、如果根节点的值比左子树最右节点的值小则不是二叉搜索树
	 * 3、如果根节点的值比右子树最左节点的值大则不是二叉搜索树
	 * 4、如果以上3个步骤都满足则原二叉树是二叉搜索树
	 * 
	 * 如果将第二种方法应用与本题，那么很容易得到一个递归程序
	 * 1、如果空节点返回0
	 * 2、如果是叶子节点返回1
	 * 3、如果左子树不是二叉搜索树，返回-1
	 * 4、如果右子不是二叉搜索树，返回-1
	 * 5、如果节点的值是否小于左子树的最右节点的值，返回-1
	 * 6、如果节点的值大于右子树的最左节点的值，返回-1
	 */
	public TreeNode getMaxSearchTree(TreeNode root) {
		MAX_SEARCH_TREE = null;
		MAX_SEARCH_TREE_SIZE = 0;
		getMaxSearchTreeCore(root);
		return MAX_SEARCH_TREE;
	}

	
	private int getMaxSearchTreeCore(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null) {
			if (MAX_SEARCH_TREE_SIZE == 0) {
				MAX_SEARCH_TREE = root;
				MAX_SEARCH_TREE_SIZE = 1;
			}
			return 1;
		}
		int sizeLeft = -1;
		int sizeRight = -1;
		if ((sizeLeft = getMaxSearchTreeCore(root.left)) < 0 
				|| (sizeRight = getMaxSearchTreeCore(root.right)) < 0
				|| (root.left != null && getMostRightNode(root.left).val > root.val)
				|| (root.right != null && getMostLeftNode(root.right).val < root.val))
			return -1;
		int sizeTotal = sizeLeft + sizeRight + 1;
		if (sizeTotal > MAX_SEARCH_TREE_SIZE) {
			MAX_SEARCH_TREE = root;
			MAX_SEARCH_TREE_SIZE = sizeTotal;
		}
		return sizeTotal;
	}
	
	private TreeNode getMostRightNode(TreeNode root) {
		assert root != null;
		while (root.right != null)
			root = root.right;
		return root;
	}
	
	private TreeNode getMostLeftNode(TreeNode root) {
		assert root != null;
		while (root.left != null)
			root = root.left;
		return root;
	}
	
	public static void main(String[] args) throws Throwable {
		TreeNode root = new SerializeTree().deserialize("6,1,0,#,#,3,#,#,12,10,4,2,#,#,"
				+ "5,#,#,14,11,#,#,15,#,#,13,20,#,#,16,#,#,");
		System.out.println(new MaxSearchTree().getMaxSearchTree(root));
	}

}
