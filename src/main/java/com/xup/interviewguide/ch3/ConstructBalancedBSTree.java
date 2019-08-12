package com.xup.interviewguide.ch3;

import com.xup.interviewguide.ch2.TransferTree.TreeNode;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConstructBalancedBSTree {

    public TreeNode construct(ArrayList<Integer> list) {
        if (list == null || list.size() < 1)
            return null;
       return constructCore(list, 0, list.size());
    }

    private TreeNode constructCore(ArrayList<Integer> list, int start, int end) {
        if (list == null || start >= end || end > list.size())
            return null;
        if (start + 1 == end)
            return new TreeNode(list.get(start));
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = constructCore(list, start, mid);
        root.right = constructCore(list, mid + 1, end);
        return root;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = (ArrayList<Integer>) Stream.iterate(1, i->i+1)
                .limit(11)
                .collect(Collectors.toList());
        System.out.println(new ConstructBalancedBSTree().construct(list));
        new PrintTree().print(new ConstructBalancedBSTree().construct(list),1, PrintTree.NodeType.ROOT);

    }
}
