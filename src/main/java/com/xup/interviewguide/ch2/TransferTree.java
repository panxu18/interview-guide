package com.xup.interviewguide.ch2;

import java.util.Stack;

public class TransferTree {

	public static class TreeNode {
	    public int val = 0;
	    public TreeNode left = null;
	    public TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;

	    }
	    
	    public static String serialize(TreeNode root) {
	        StringBuilder sb = new StringBuilder();
	        preOrderToSerialize(root,sb);
	        return sb.toString();
	    }

	    private static void preOrderToSerialize(TreeNode root, StringBuilder sb) {
	        if (root == null) {
	            sb.append("#,");
	            return;
	        }
	        sb.append(root.val);
	        sb.append(',');
	        preOrderToSerialize(root.left, sb);
	        preOrderToSerialize(root.right, sb);
	    }
	    
	    static int RECUR_INDEX = 0;
	    public static TreeNode deserializeRecur(String str) throws Throwable {
	    	if (str == null || str.length() <= 0 || str.charAt(0) == '#')
	            return null;
	    	String[] strs = str.split(",");
	    	RECUR_INDEX = 0;
	    	return deserializeRecurCore(strs);
	    }
	    
	    public static TreeNode deserializeRecurCore(String[] strs) throws Throwable {
	    	if (RECUR_INDEX >= strs.length)
	    		throw new Throwable("input is not a tree");
	    	String val = strs[RECUR_INDEX++];
	    	if (val.equals("#"))
	    		return null;
	    	TreeNode root = new TreeNode(Integer.valueOf(val));
	    	root.left = deserializeRecurCore(strs);
	    	root.right = deserializeRecurCore(strs);
	    	return root;
	    }
	    
	    public static TreeNode deserializeWithLoop(String str) {
	    	if (str == null || str.length() <= 0 || str.charAt(0) == '#')
	            return null;
	        String[] strs = str.split(",");
	        Stack<TreeNode> stack = new Stack<>();
	        int i = 0;
	        stack.add(new TreeNode(Integer.valueOf(strs[i++])));
	        TreeNode root = stack.peek();
	        while (!stack.isEmpty() || (i < strs.length && !strs[i].equals("#"))) {
	            while (i < strs.length && !strs[i].equals("#") ) {
	                stack.peek().left = new TreeNode(Integer.valueOf(strs[i++]));
	                stack.add(stack.peek().left);
	            }
	            TreeNode p = null;
	            p = stack.pop();
	            i++;
	            if (i < strs.length && p != null && !strs[i].equals("#")) {
	                p.right = new TreeNode(Integer.valueOf(strs[i]));
	                stack.add(p.right);
	                i++;
	            }
	        }
	        return root;
	    }

		@Override
		public String toString() {
			return "[val=" + val + "]";
		}
	    
	    
	}
	TreeNode head = new TreeNode(-1);
	TreeNode tail = head;
	
	/*
	 * 利用中序遍历构建链表
	 */
	public void convertCore(TreeNode root) {
		if (root == null)
			return;
		convertCore(root.left);
		root.left = tail;
		tail.right = root;
		tail = root;
		
	}
	
}
