package com.xup.interviewguide.ch3;

import java.util.Scanner;

public class NumsOfTree {

	public static long getNums(int n) {
		assert n > 0;
		long[] f = new long[n + 1];
		f[0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				f[i] += (f[j - 1] * f[i - j]); 
				f[i] %= 1_000_000_007;
			}
		}
		return f[n];
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		System.out.println(getNums(n));
	}
	
//	public static void main(String[] args){
//        Scanner in = new Scanner(System.in);
//        while(in.hasNext()){
//            int N = in.nextInt();
//            if(N<2){
//                System.out.println(1);
//                continue;
//            }
//            long[] dp = new long[N+1];
//            dp[0] = 1;
//            for(int i = 1;i<=N;i++){
//                for(int j = 1;j<=i;j++){
//                    dp[i] += (dp[j-1]*dp[i-j])%1000000007;
//                    dp[i] %= 1000000007;
//                }
//            }
//            System.out.println(dp[N]%1000000007);
//        }
//        in.close();
//    }
}
