package com.xup.interviewguide.ch3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ComputePosOrder {
	
	public static void setPos(int[] preOrder, int preStart, int preEnd,
			int[] inOrder, int inStart, int inEnd,
			int[] posOrder, int posStart, int posEnd,
			HashMap<Integer, Integer> map) {
		if (preStart >= preEnd)
			return;
		int mid = map.get(preOrder[preStart]);
		posOrder[posEnd - 1] = preOrder[preStart];
		setPos(preOrder, mid - inStart + preStart + 1, preEnd, inOrder, mid + 1, inEnd, posOrder, posStart, posEnd - 1, map);
		setPos(preOrder, preStart + 1, mid - inStart + preStart + 1, inOrder, inStart, mid, posOrder, posStart, posEnd - (inEnd - mid), map);
	}
	
	/**
	 * @author xupan
	 * @date 2019年8月15日
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int length = input.nextInt();
		input.nextLine();
		int[] preOrder = Arrays.stream(input.nextLine().split(" "))
				.mapToInt(Integer::parseInt).toArray();
		int[] inOrder = Arrays.stream(input.nextLine().split(" "))
				.mapToInt(Integer::parseInt).toArray();
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inOrder.length; i++) {
			map.put(inOrder[i], i);
		}
		int[] posOrder = new int[inOrder.length];
		setPos(preOrder, 0, length, inOrder, 0, length, posOrder, 0, length, map);
		for (int i = 0; i < length - 1; i++)
			System.out.print(posOrder[i] + " ");
		System.out.print(posOrder[length - 1] + "\n");
	}

}
