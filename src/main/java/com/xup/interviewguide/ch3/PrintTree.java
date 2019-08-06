package com.xup.interviewguide.ch3;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

public class PrintTree {
	enum NodeType {
		ROOT,LEFT,RIGHT;
	}
	public void print(TreeNode root, int level, NodeType type) {
		if (root != null) {
			print(root.right, level + 1, NodeType.RIGHT);
			String s = "";
			switch (type) {
			case ROOT:
				s = "H";
				break;
			case LEFT:
				s = "^";
				break;
			case RIGHT:
				s = "v";
				break;
			default:
				break;
			}
			System.out.println(String.format("%1$s %2$-7s", getOffset(level), s+root.val+s));
			print(root.left, level + 1, NodeType.LEFT);
		}
	}
	
	private String getOffset(int len) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len * 7; i++)
			sb.append(" ");
		return sb.toString();
	}
	
	public static void main(String[] args) throws Throwable {
		TreeNode root = TreeNode.deserialize("1,2,#,4,7,#,#,8,#,11,13,#,#,14,#,#,"
				+ "3,5,9,12,15,#,#,16,#,#,#,10,#,#,6,#,#,");
		new PrintTree().print(root, 0, NodeType.ROOT);
	}
}
