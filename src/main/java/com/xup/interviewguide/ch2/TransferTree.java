package com.xup.interviewguide.ch2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class TransferTree {

	public static class TreeNode {
	    public int val = 0;
	    public TreeNode left = null;
	    public TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;

	    }
	    
	    public int getVal() {
	    	return val;
	    }
	    
	    public static TreeNode buildTree() {
	    	Scanner in = new Scanner(System.in);
	    	if (in.hasNext()) {
	    		in.nextLine();
	    		return buildTreeCore(in);
	    	}
	    	return null;
	    }
	    
	    public static TreeNode buildTreeCore(Scanner in) {
	    	while (in.hasNext()) {
	    		int val = in.nextInt();
	    		TreeNode root = new TreeNode(val);
	    		int valLeft = in.nextInt();
	    		int valRight = in.nextInt();
	    		if (valLeft != 0)
	    			root.left = buildTreeCore(in);
	    		if (valRight != 0)
	    			root.right = buildTreeCore(in);
	    		return root;
	    	}
	    	return null;
	    }
	    
	    /*
	     * 二叉树序列化
	     * 以先序遍历的方式序列化二叉树，节点之间用逗号隔开，空节点用#表示
	     */
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
	    
	    public static TreeNode deserialize(String str) {
			if (str == null || str.charAt(0) == '#')
				return null;
			String[] strs = str.split(",");
			LinkedList<String> result = Arrays.stream(strs)
					.collect(Collectors.toCollection(LinkedList::new));
			return deserializeCore(result);
		}
	    
	    private static TreeNode deserializeCore(LinkedList<String> list) {
			String val = null;
			if (list.isEmpty() || (val = list.pollFirst()).equals("#"))
				return null;
			TreeNode root = new TreeNode(Integer.valueOf(val));
			root.left = deserializeCore(list);
			root.right = deserializeCore(list);
			return root;
		}
	    
	    static int RECUR_INDEX = 0;
	    
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
			return serialize(this);
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
