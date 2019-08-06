package com.xup.interviewguide.ch3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

public class SerializeTree {
	
	public String serializeTree(TreeNode root) {
		if (root == null)
			return "#,";
		StringBuffer sb = new StringBuffer();
		sb.append(root.val);
		sb.append(",");
		sb.append(serializeTree(root.left));
		sb.append(serializeTree(root.right));
		return sb.toString();
	}
	
	public TreeNode deserialize(String str) {
		if (str == null || str.charAt(0) == '#')
			return null;
		String[] strs = str.split(",");
		LinkedList<String> result = Arrays.stream(strs)
				.collect(Collectors.toCollection(LinkedList::new));
		return deserializeCore(result);
	}

	private TreeNode deserializeCore(LinkedList<String> list) {
		String val = null;
		if (list.isEmpty() || (val = list.pollFirst()).equals("#"))
			return null;
		TreeNode root = new TreeNode(Integer.valueOf(val));
		root.left = deserializeCore(list);
		root.right = deserializeCore(list);
		return root;
	}
	
	public static void main(String[] args) throws Throwable {
		TreeNode root = new SerializeTree().deserialize("1,2,#,4,7,#,#,8,#,11,13,#,#,14,#,#,"
				+ "3,5,9,12,15,#,#,16,#,#,#,10,#,#,6,#,#,");
		System.out.println(TreeNode.serialize(root));
	}

}
