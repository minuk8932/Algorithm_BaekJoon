package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2056번: 작업
 *
 *	@see https://www.acmicpc.net/problem/2056/
 *
 */
public class Boj2056 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] path = new ArrayList[N];
		int[] cost = new int[N];
		
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cost[i] = Integer.parseInt(st.nextToken());
			
			int count = Integer.parseInt(st.nextToken());
			while(count-- > 0) {
				path[i].add(Integer.parseInt(st.nextToken()) - 1);
			}
		}
		
		System.out.println(search(N, path, cost));
	}
	
	private static int search(int n, ArrayList<Integer>[] list, int[] cost) {
		int res = 0;
		int[] dp = new int[n];
		
		for(int start = 0; start < n; start++) {
			int max = 0;
			
			for(int edge: list[start]) {			// 현 작업에 연결된 작업 중 가장 큰 값
				if(max < dp[edge]) max = dp[edge]; 
			}
			
			dp[start] = max + cost[start];			// 값 누적
			if(res < dp[start]) res = dp[start];	// 작업이 단계별 완료가 되었을때 임의의 노드 start에서 가장 큰 결과 값
		}
		
		return res;
	}
}
