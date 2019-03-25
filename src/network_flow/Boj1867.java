package network_flow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1867번: 돌멩이 제거
 *
 *	@see https://www.acmicpc.net/problem/1867/
 *
 */
public class Boj1867 {
	private static ArrayList<Integer>[] connected;
	private static int[] stones; 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		connected = new ArrayList[n];		
		for(int i = 0; i < n; i++) {
			connected[i] = new ArrayList<>();
		}
		
		while(k-- > 0) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			
			connected[row].add(col);			// 라인별 제거 가능한 돌멩이 정보
		}
		
		System.out.println(bipartiteMatch(n));
	}
	
	private static int bipartiteMatch(int N) {
		int result = 0;
		
		stones = new int[N];
		Arrays.fill(stones, -1);
		
		for(int i = 0; i < N; i++) {
			boolean[] visit = new boolean[N];
			if(dfs(visit, i)) result++;			// i번에서 돌멩이를 제거한 경우
		}
		
		return result;
	}
	
	private static boolean dfs(boolean[] visit, int current) {
		if(visit[current]) return false;
		visit[current] = true;
		
		for(int next: connected[current]) {
			if(stones[next] == -1 || dfs(visit, stones[next])) {
				stones[next] = current;								// 돌멩이를 제거한 라인을 입력
				return true;
			}
		}
		
		return false;
	}
}
