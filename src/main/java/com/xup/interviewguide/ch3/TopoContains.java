package com.xup.interviewguide.ch3;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

public class TopoContains {
	public boolean containsCore(TreeNode root1, TreeNode root2) {
		if (root2 == null)
			return true;
		else if (root2 != null && root1 != null && root1.val == root2.val) {
			return containsCore(root1.left, root2.left)
					&& containsCore(root1.right, root2.right);
		} else
			return false;
	}
	
	/*
	 * 遍历第一个树，如果遇到与二个树的根节点值相同的节点，就判断该节点构成的
	 * 拓扑结构是否包含第二个树
	 */
	public boolean contains(TreeNode root1, TreeNode root2) {
		if (root1 == null) {
			if (root2 == null)
				return true;
			return false;
		}
		return containsCore(root1, root2) 
				|| contains(root1.left, root2)
				|| contains(root1.right, root2);
	}

	public static void main(String[] args) {
		TreeNode root1 = TreeNode.deserialize("1,2,4,8,#,#,9,#,#,5,10,#,#,#,3,6,#,#,7,#,#");
		TreeNode root2 = TreeNode.deserialize("2,4,9,#,#,#,5,#,#,");
		System.out.println(new TopoContains().contains(root1, root2));
	}
}
