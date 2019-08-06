package com.xup.interviewguide.ch3;

import java.util.ArrayList;
import java.util.LinkedList;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

public class LongestPathInTree {
	
	public ArrayList<Integer> getLongestPath(TreeNode root, int k) {
		if (root == null)
			return new ArrayList<>(0);
		ArrayList<Integer> result = new ArrayList<>();
		LinkedList<Integer> path = new LinkedList<>();
		getLongestPathCore(root, 0, k, path, result);
		return result;
		
	}
	
	private void getLongestPathCore(TreeNode root, int sum, int k,
			LinkedList<Integer> path, ArrayList<Integer> result) {
		if (root == null)
			return;
		path.add(root.val);
		if (sum + root.val == k) {
			if (path.size() > result.size()) {
				result.clear();
				result.addAll(path);
			}
		}
		getLongestPathCore(root.left, sum + root.val, k, path, result);
		getLongestPathCore(root.right, sum + root.val, k, path, result);
		path.pollLast();
	}
	
	public static void main(String[] args) throws Throwable {
		TreeNode root = new SerializeTree().deserialize("1,2,#,4,7,#,#,8,#,11,13,#,#,14,#,#,"
				+ "3,5,9,12,15,#,#,16,#,#,#,10,#,#,6,#,#,");
		System.out.println(new LongestPathInTree().getLongestPath(root, 14));
	}

}
