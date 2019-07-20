package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * 	@author exponential-e
 *	백준 12849번: 본대 산책
 *
 *	@see https://www.acmicpc.net/problem/12849/
 *
 */
public class Boj12849 {
	private static final int MOD = 1_000_000_007;
	
	private static ArrayList<Integer>[] path = new ArrayList[8];
	private static long[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int D = Integer.parseInt(br.readLine());
		init(D);
		
		System.out.println(recursion(D, 0));
	}
	
	private static long recursion(int D, int current) {
		if(D == 0) {
			if(current != 0) return 0;		// 시간이 지났는데 못 돌아옴
			else return 1;					// 산책 후 돌아오는 경우
		}
		
		if(dp[current][D] != 0) return dp[current][D] % MOD;		// 이미 지나간 경로
		
		for(int next: path[current]) {
			dp[current][D] = ((dp[current][D] % MOD) + (recursion(D - 1, next) % MOD)) % MOD;		// 본대 산책
		}
		
		return dp[current][D] % MOD;
	}
	
	private static void init(int time) {
		dp = new long[8][time + 1];
		
		for(int i = 0; i < path.length; i++) {
			path[i] = new ArrayList<>();
		}
		
		path[0].add(1);
		path[1].add(0);
		path[0].add(2);
		path[2].add(0);
		path[2].add(1);
		path[1].add(2);
		path[2].add(3);
		path[3].add(2);
		path[2].add(4);
		path[4].add(2);
		path[3].add(1);
		path[1].add(3);
		path[4].add(3);
		path[3].add(4);
		path[5].add(3);
		path[3].add(5);
		path[4].add(5);
		path[5].add(4);
		path[4].add(6);
		path[6].add(4);
		path[5].add(7);
		path[7].add(5);
		path[6].add(7);
		path[7].add(6);
	}
}
