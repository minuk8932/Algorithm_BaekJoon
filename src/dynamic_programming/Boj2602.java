package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 2602번: 돌다리 건너기
 *
 *	@see https://www.acmicpc.net/problem/2602/
 *
 */
public class Boj2602 {
	private static int[][][] dp; 
	private static char[] seq;
	private static char[][] brg;
	
	private static int n, m;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		seq = br.readLine().toCharArray();
		
		brg = new char[2][];
		for(int i = 0; i < 2; i++) {
			brg[i] = br.readLine().toCharArray();
		}
		
		n = brg[0].length;
		m = seq.length;
		
		dp = new int[n + 1][m + 1][2];
		System.out.println(recursion(0, 0, 0) + recursion(1, 0, 0));
	}
	
	private static int recursion(int used, int current, int pos) {
		if(pos == m) return 1;			// 규칙대로 건넌 경우
		if(current == n) return 0;
		
		if(dp[current][pos][used] != 0) return dp[current][pos][used];
		
		int res = 0;		
		if(used == 0) res += stacking(current, pos, brg[used], used);				// 천사 다리 밟을 차례
		else res += stacking(current, pos, brg[used], used);
		
		return dp[current][pos][used] = res;
	}
	
	private static int stacking(int cur, int p, char[] arr, int u) {
		int sum = 0;
		
		for(int nxt = cur; nxt < arr.length; nxt++){
			if(arr[nxt] == seq[p]) sum += recursion((u + 1) % 2, nxt + 1 , p + 1);	// 올바른 다리의 올바른 돌을 밟은 경우
		}

		return sum;
	}
}
