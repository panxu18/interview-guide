package com.xup.interviewguide.ch3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

/*
 * 书上的解法有问题，
 * 标准1要求逆序输出边界节点，也就是除了右边界之外的节点都应该
 * 按照从左至右，从上至下的顺序输出。
 * 对于标准1可以按照以下方法输出
 * 1、通过层次遍历或者带层编号的先序遍历得到每一层的左右边界节点
 * 2、先序遍历，输出左边界节点和不是右边界的叶子节点
 * 3、从下到上输出右边界节点
 * 通过先序遍历可以保证左边的节点一定比右边的节点先输出，
 * 右边界节点从下到上输出保证逆时针的顺序
 * 
 * 标准2    题目意思和给的实例结果感觉有点不符合
 */
public class PrintEdgeOfTree {

	public ArrayList<TreeNode> printEdge1(TreeNode root) {
		ArrayList<ArrayList<TreeNode>> edges = new ArrayList<ArrayList<TreeNode>> ();
//		edges = leverOrderSetEdeg(root);
		preOrderSetEdge(root, 0, edges);
		ArrayList<TreeNode> result = new ArrayList<>();
		addLeftEdge(root, 0, edges, result);
		addRightEdge(edges, result);
		return result;
		
	}
	
	/*
	 * 先序遍历左边界和叶子节点
	 */
	private void addLeftEdge(TreeNode root, int level, ArrayList<ArrayList<TreeNode>> edges,
			ArrayList<TreeNode> result) {
		if (root != null) {
			if (root == edges.get(level).get(0)
					|| (root.left == null && root.right == null
					&& edges.get(level).size() > 1 && edges.get(level).get(1) != root))
				result.add(root);
			addLeftEdge(root.left, level + 1, edges, result);
			addLeftEdge(root.right, level + 1, edges, result);
		}
	}
	
	private void addRightEdge( ArrayList<ArrayList<TreeNode>> edges,
			ArrayList<TreeNode> result) {
		ArrayList<TreeNode> rightEdges = (ArrayList<TreeNode>) edges.stream()
				.filter(a->a.size()>1)
				.map(list->list.get(1))
				.collect(Collectors.toList());
		Collections.reverse(rightEdges);
		result.addAll(rightEdges);
	}

	/*
	 * 通过层次遍历获得每层左右边界节点,空间复杂度为O(n)
	 */
	private ArrayList<ArrayList<TreeNode>> leverOrderSetEdeg(TreeNode root) {
		if (root == null)
			return new ArrayList<>(0);
		ArrayList<ArrayList<TreeNode>> edges = new ArrayList<>();
		ArrayList<TreeNode> edge = null;
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.addLast(root);
		TreeNode p = null;
		while (!queue.isEmpty()) {
			edge = new ArrayList<>(2);
			int width = queue.size();
			for (int i = 1; i <= width; i++) {
				p = queue.poll();
				if (i == 1 || i == width)
					edge.add(p);
				if (p.left != null)
					queue.addLast(p.left);
				if (p.right != null)
					queue.addLast(p.right);
			}
			edges.add(edge);
		}
		return edges;
	}

	/*
	 * 通过先序遍历保存每一层的左右边界,空间复杂度为O(h)
	 */
	private void preOrderSetEdge2(TreeNode root, int level,
			ArrayList<ArrayList<TreeNode>> edges) {
		if (root != null) {
			if (edges.size() <= level) {
				edges.add(new ArrayList<>(2));
			}
			int size = edges.get(level).size();
			if (size == 0 || size == 1)
				edges.get(level).add(root);
			else
				edges.get(level).set(1, root);
			preOrderSetEdge(root.left, level + 1, edges);
			preOrderSetEdge(root.right, level + 1, edges);
		}
	}
	
	/*
	 * 通过先序遍历保存每一层的左右边界,空间复杂度为O(h)
	 */
	private void preOrderSetEdge(TreeNode root, int level,
			ArrayList<ArrayList<TreeNode>> edges) {
		if (root != null) {
			if (edges.size() <= level) {
				edges.add(new ArrayList<>(2));
			}
			int size = edges.get(level).size();
			if (size == 0 || size == 1)
				edges.get(level).add(root);
			else
				edges.get(level).set(1, root);
			preOrderSetEdge(root.left, level + 1, edges);
			preOrderSetEdge(root.right, level + 1, edges);
		}
	}

	public static void main(String[] args) throws Throwable {
		TreeNode root = TreeNode.deserializeRecur("1,2,#,4,7,#,#,8,#,11,13,#,#,14,#,#,"
				+ "3,5,9,12,15,#,#,16,#,#,#,10,#,#,6,#,#,");
		System.out.println(new PrintEdgeOfTree().printEdge1(root));
	}

}
