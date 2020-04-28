package Floyd_Warshall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11265번: 끝나지 않는 파티
 *
 *	@see https://www.acmicpc.net/problem/11265/
 *
 */
public class Boj11265 {	
	private static int[][] map;
	
	private static final String NEW_LINE = "\n";
	private static final String YES = "Enjoy other party";
	private static final String NO = "Stay here";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		
		for(int from = 1; from < N + 1; from++) {
			st = new StringTokenizer(br.readLine());
			
			for(int to = 1; to < N + 1; to++) {
				map[from][to] = Integer.parseInt(st.nextToken());
			}
		}
		
		floydWashall(N);
		
		StringBuilder sb = new StringBuilder();
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			sb.append(map[start][end] <= time ? YES : NO).append(NEW_LINE);
		}

		System.out.println(sb);
	}
	
	private static void floydWashall(int n) {			// 경로 개선
		for(int v = 1; v < n + 1; v++) {
			for(int s = 1; s < n + 1; s++) {
				for(int e = 1; e < n + 1; e++) {
					if(map[s][e] > map[s][v] + map[v][e]) {
						map[s][e] = map[s][v] + map[v][e];
					}
				}
			}
		}
	}
}
