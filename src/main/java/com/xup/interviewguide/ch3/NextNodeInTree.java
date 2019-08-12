package com.xup.interviewguide.ch3;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

import java.util.Scanner;
import java.util.Stack;

public class NextNodeInTree {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            TreeNode root = TreeNode.buildTree(input);
            int k = input.nextInt();
            TreeNode result = getNextNode(root, k);
            System.out.println(result == null ? 0 : result.val);
        }
    }

    private static TreeNode getNextNode(TreeNode root, int k) {
        TreeNode result = null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            if (!stack.isEmpty()) {
                if (stack.peek().val == k) {
                    if ((p = stack.pop().right) != null) {
                        while (p.left != null)
                            p = p.left;
                        result = p;
                    } else {
                        result = stack.isEmpty() ? null : stack.peek();
                    }
                        break;
                }
                p = stack.pop().right;
            }
        }
        return result;
    }
}
