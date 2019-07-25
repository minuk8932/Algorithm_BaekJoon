package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 12101번: 1, 2, 3 더하기 2
 *
 *	@see https://www.acmicpc.net/problem/12101/
 *
 */
public class Boj12101 {
	private static ArrayList<Sort> list = new ArrayList<>();
	private static int[][] dp;
	
	private static class Sort implements Comparable<Sort>{
		String input;
		
		public Sort(String input) {
			this.input = input;
		}

		@Override
		public int compareTo(Sort s) {
			return this.input.compareTo(s.input);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		dp = new int[n + 1][4];
		recursion(n, 0, "");
		
		Collections.sort(list);
		System.out.println(list.size() < k ? -1: list.get(k - 1).input);		// k가 범위를 넘어가는 경우 -1
	}
	
	private static int recursion(int n, int sub, String formula) {
		if(n == 0) {
			list.add(new Sort(formula));
			return 1;
		}
		if(n < 0) return 0;
		
		for(int i = 1; i < 4; i++) {
			if(formula.equals("")) dp[n][sub] = recursion(n - i, i, formula + i) + dp[n][sub];		// 초기 입력값
			else dp[n][sub] = recursion(n - i, i, formula + "+" + i) + dp[n][sub];					// 누적 dp 입력
		}
		
		return dp[n][sub];
	}
}
