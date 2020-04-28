package Floyd_Warshall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 14938번: 서강 그라운드
 *
 *	@see https://www.acmicpc.net/problem/14938/
 *
 */
public class Boj14938 {
	private static int[][] route;
	private static final int INF = 1_000_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[] items = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		route = new int[n][n];
		for(int i = 0; i < n; i++) {
			Arrays.fill(route[i], INF);
		}
		
		while(r-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int l = Integer.parseInt(st.nextToken());
			
			route[a][b] = route[b][a] = l;
		}
		
		System.out.println(getMax(n, m, items));
	}
	
	private static int getMax(int n, int m, int[] it) {
		floydWashall(n, m);
		
		int max = 0;
		for(int start = 0; start < n; start++) {
			int sum = it[start] + search(n, m, it, start);				// 총 획득한 아이템 갯수
			if(sum > max) max = sum;
		}		
		
		return max;
	}
	
	private static void floydWashall(int n, int m) {
		for(int v = 0; v < n; v++) {
			for(int s = 0; s < n; s++) {
				for(int e = 0; e < n; e++) {
					if(route[s][e] > route[s][v] + route[v][e]) {		// 도달 가능한 마을까지 최단 경로
						route[s][e] = route[s][v] + route[v][e];
					}
				}
			}
		}
	}
	
	private static int search(int n, int m, int[] farm, int s) {
		int res = 0;
		for(int e = 0; e < n; e++) {
			if(s == e) continue;
			if(route[s][e] <= m) res += farm[e];
		}
		
		return res;
	}
}
