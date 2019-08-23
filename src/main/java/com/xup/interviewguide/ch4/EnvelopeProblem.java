package com.xup.interviewguide.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class EnvelopeProblem {
	static class Envelope implements Comparable<Envelope>{
		int len = 0;
		int width = 0;
		
		public Envelope(int l, int w) {
			len = l;
			width = w;
		}

		public int getLen() {
			return len;
		}

		public int getWidth() {
			return width;
		}

		@Override
		public int compareTo(Envelope o) {
			return len == o.len ? o.width - width : len - o.len;
		}

		@Override
		public String toString() {
			return "Envelope [len=" + len + ", width=" + width + "]";
		}
		
		
	}
	
	private static int process(PriorityQueue<Envelope> queue) {
		int[] list = new int[queue.size()];
		int len = 0;
		while (!queue.isEmpty()) {
			len = insert(list, len, queue.poll().getWidth());
		}
		return len;
	}
	
	private static int process(int[] q) {
		int[] list = new int[q.length];
		int len = 0;
		for (int i = 0; i < q.length; i++)
			len = insert(list, len, q[i]);
		return len;
	}
	
	private static int insert(int[] list, int size, int k) {
		int start = 0;
		int end = size - 1;
		if (size == 0 || list[end] < k) {
			list[size] = k;
			return size + 1;
		}
		while (start <= end) {
			int mid = (start + end) / 2;
			if (list[mid] < k)
				start = mid + 1;
			else
				end = mid - 1;
		}
		list[start] = k;
		return size;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = br.readLine().split(" ");
		int n = Integer.parseInt(strs[0]);
		if (n == 0) {
			System.out.println(0);
			return;
		}
		int[][] list = new int[n][2];
		for (int i = 0; i < n; i++) {
			strs = br.readLine().split(" ");
			list[i][0] = Integer.parseInt(strs[0]);
			list[i][1] = Integer.parseInt(strs[1]);
		}
		int[] list2 = Arrays.stream(list).sorted((a, b)-> {
			return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
		}).mapToInt(a->a[1]).toArray();
//		PriorityQueue<Envelope> queue = new PriorityQueue<>();
//		for (int i = 0; i < n; i++) {
//			strs = br.readLine().split(" ");
//			Envelope env = new Envelope(Integer.parseInt(strs[0]),
//					Integer.parseInt(strs[1]));
//			queue.add(env);
//		}
//		System.out.println(process(queue));
		System.out.println(process(list2));
	}

}
