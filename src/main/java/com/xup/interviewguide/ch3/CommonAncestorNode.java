package com.xup.interviewguide.ch3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;


import com.xup.interviewguide.ch2.TransferTree.TreeNode;
import com.xup.interviewguide.ch3.PrintTree.NodeType;

public class CommonAncestorNode {

    /*
    * 通过比较两个节点中序或者后序遍历时到根节点的路径，
    * 就可以得到公共祖先。采用这种思路，需要保存所有节点
    * 到根节点的路径，每个节点的路径的长度为O(h)，同时在
    * 比较路径时需要花的时间为为O(h)。所以这个方法的时间
    * 复杂度为O(n),空间复杂度为O(n*h)。
    *
    * 在上面的方法中，存在重复的比较，例如，一个节点的深度
    * 很深，也就是它到根节点的路径很长。现在要求它的左节点
    * 和右节点的公共祖先，那么比较路径时，从根节点开始一直到
    * 当前节点才能发现公共节点。实际上已经知道这两个节点
    * 分别位于一棵子树的左边和右边，那么公共祖先节点就是
    * 当前子树的根节点。
    *
    * 那么怎么判断需要比较的两个节点位于哪一棵子树上？
    * 通过先查找左子树后查找右子树的顺序，查找到第一个节点之后，
    * 首先是在以第一个节点为根的子树中查找第二个节点，然后
    * 依次在其祖先节点为根的子树中查找第二节点。查找到第二个
    * 节点时就知道两个节点分别位于当前子树的左右两边，所以公共
    * 祖先就是当前子树的根节点。
    *
    * 下面的实现代码中通过commonAncestor表示当前子树的根
    * 节点，nextCommonAncestor表示下一棵子树的根节点。
    * 
     */
	public static TreeNode getCommonAncestor(TreeNode root, int k1, int k2) {
		if (root == null)
			return null;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode commonAncestor = null;
		TreeNode pre = null;
		TreeNode cur = root;
		while (!stack.isEmpty() || cur != null) {
			if (cur != null) {
				if (cur.val == k1 || cur.val == k2) {
					if (commonAncestor == null)
						commonAncestor = cur;
					else
						break;
				}
				stack.push(cur);
				cur = cur.left;
			} else if (stack.peek().right == null 
					|| pre == stack.peek().right){
				pre = stack.peek();
				if (stack.pop() == commonAncestor)
					commonAncestor = stack.peek();
			} else 
				cur = stack.peek().right;
		}
		return commonAncestor;
	}
	
	public static int getCommonAncestor() {
		Scanner input = new Scanner(System.in);
		int nums = input.nextInt();
		input.nextInt();
		int[][] list = new int[nums][3];
		for (int i = 0; i < nums; i++)
			for (int j = 0; j < 3; j++)
				list[i][j] = input.nextInt();
		
		int k1 = input.nextInt();
		int k2 = input.nextInt();
		int[] stack = new int[nums];
		int top = -1;
		int commonAncestor = -1;
		int nextAncestor = -1;
		input.nextLine();
		for (int[] l : list) {
			if (l[0] == k1 || l[0] == k2) {
				if (commonAncestor == -1) {
					commonAncestor = l[0];
					nextAncestor = top == -1 ? -1 : stack[top];
				}
				else
					break;
			}
			stack[++top] = l[0];
			if (l[1] == 0 && stack[top--] == nextAncestor) {
				commonAncestor = nextAncestor;
				nextAncestor = top == -1 ? -1 : stack[top];
			}
			if (l[2] == 0 && stack[top--] == nextAncestor)  {
				commonAncestor = nextAncestor;
				nextAncestor = top == -1 ? -1 : stack[top];
			}
		}
		
		return commonAncestor;
	}
	
	/*
	 * 查找指定节点当前所在子树的根节点
	 */
	private static int getAncestor(HashMap<Integer, Integer> ancestorMap, int key) {
		if (!ancestorMap.containsKey(key))
			return 0;
		int result = key;
		// 查找当前子树的根节点
		while (ancestorMap.get(result) != result) {
			result = ancestorMap.get(result);
		}
		int temp;
		// 压缩路径
		while (key != (temp = ancestorMap.get(key))) {
			ancestorMap.put(key, result);
			key = temp;
		}
		return result;
		
	}
	/*
	 * 在m个查询条件同时查询时，需要在遍历时需要知道m对节点分别在
	 * 哪一棵子树，所以需要保存m个当前子树的根节点以及下一棵子树的
	 * 根节点。
	 */
	public static ArrayList<Integer> getCommonAncestorWithMultQuery(TreeNode root, ArrayList<ArrayList<Integer>> query) {
		if (root == null)
			return null;
		
		HashMap<Integer, ArrayList<Integer>> queryMap = new HashMap<>();
		for (ArrayList<Integer> l : query) {
			if (queryMap.get(l.get(0)) == null)
				queryMap.put(l.get(0), new ArrayList<>());
			queryMap.get(l.get(0)).add(l.get(1));
			if (queryMap.get(l.get(1)) == null)
				queryMap.put(l.get(1), new ArrayList<>());
			queryMap.get(l.get(1)).add(l.get(0));
		}
		HashMap<Integer, Integer> ancestorMap = new HashMap<>();
		HashMap<Integer, HashMap<Integer, Integer>> answer = new HashMap<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode pre = null;
		TreeNode cur = root;
		while (!stack.isEmpty() || cur != null) {
			if (cur != null) {
				// 设置初始祖先节点为自身
				ancestorMap.put(cur.val, cur.val);
				/*
				 *  如果查询条件中前一个节点的祖先节点已经求出来了，
				 *  那么两个节点的共同节点为前一个节点的祖先
				 */
				if (queryMap.get(cur.val) != null) {
					if (cur.val == 38)
						System.out.println("test");
					for (Integer v : queryMap.get(cur.val)) {
						int ancestor = getAncestor(ancestorMap, v);
						if (ancestor != 0
								&& (answer.get(cur.val) != null
								|| answer.put(cur.val, new HashMap<>()) == null))
							answer.get(cur.val).put(v, ancestor);
					}
				}
				stack.push(cur);
				cur = cur.left;
			} else if (stack.peek().right == null 
					|| pre == stack.peek().right){
				pre = stack.pop();
				if (!stack.isEmpty())
					ancestorMap.put(pre.val, stack.peek().val);
			} else
				cur = stack.peek().right;
		}
		// 整理结果
		ArrayList<Integer> result = new ArrayList<>(query.size());
		for (ArrayList<Integer> l : query) {
			Integer r = null;
			if (answer.containsKey(l.get(0))
					&& (r = answer.get(l.get(0)).get(l.get(1))) != null) {
				result.add(r);
			} else 
				result.add(answer.get(l.get(1)).get(l.get(0)));
		}
		return result;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		TreeNode root = TreeNode.buildTree(input);
		int nums = input.nextInt();
		ArrayList<ArrayList<Integer>> query = new ArrayList<>();
		for (int i = 0; i < nums; i++) {
			ArrayList<Integer> l = new ArrayList<>(2);
			l.add(input.nextInt());
			l.add(input.nextInt());
			query.add(l);
		}
		new PrintTree().print(root, 0, NodeType.ROOT);
		getCommonAncestorWithMultQuery(root, query).stream()
		.forEach(System.out::println);;
	}
	
}
