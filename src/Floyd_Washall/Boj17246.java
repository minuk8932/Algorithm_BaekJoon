package Floyd_Washall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17246번: 씨씨
 *
 *	@see https://www.acmicpc.net/problem/17246/
 *
 */
public class Boj17246 {
	private static final String NEW_LINE = "\n";
	private static final int INF = 10_000_001;
	
	private static int[][] relate;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		init(N);
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int k = Integer.parseInt(st.nextToken());
			
			relate[a][b] = relate[b][a] = k;
		}
		
		floydWashall(N);
		
		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		
		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			sb.append(relate[x][y] == INF ? -1: relate[x][y]).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static void floydWashall(int n) {		
		for(int v = 0; v < n; v++) {
			for(int s = 0; s < n; s++) {
				for(int e = 0; e < n; e++) {							// 촌수 갱신
					if(relate[s][e] > relate[s][v] + relate[v][e]) {
						relate[s][e] = relate[s][v] + relate[v][e];
					}
				}
			}
		}
	}
	
	private static void init(int n) {
		relate = new int[n][n];
		for(int i = 0; i < n; i++) {
			Arrays.fill(relate[i], INF);
		}
	}
}
