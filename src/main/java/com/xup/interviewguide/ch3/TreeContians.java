package com.xup.interviewguide.ch3;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

public class TreeContians {
	
	public boolean contains(TreeNode root1 , TreeNode root2) {
		String str1 = root1.toString();
		String str2 = root2.toString();
		return str1.contains(str2);
	}

	public static void main(String[] args) {
		TreeNode root1 = TreeNode.deserialize("1,2,4,8,#,#,#,5,10,#,#,#,3,6,#,#,7,#,#");
		TreeNode root2 = TreeNode.deserialize("2,4,8,#,#,#,5,#,#,");
		System.out.println(new TreeContians().contains(root1, root2));
	}
}
