package Floyd_Washall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14588번: Line Friends (small)
 *
 *	@see https://www.acmicpc.net/problem/14588/
 *
 */
public class Boj14588 {	
	private static final String NEW_LINE = "\n";
	private static final int INF = 10_000_001;
	
	private static int[][] distance;
	
	private static class Line{
		int from;
		int to;
		
		public Line(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		distance = new int[N][N];
		
		Line[] friends = new Line[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			friends[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		makeFriendSetByFloydWashall(N, friends);
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			sb.append(distance[x][y] == INF ? -1: distance[x][y]).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static void makeFriendSetByFloydWashall(int n, Line[] arr) {
		for(int i = 0; i < n; i++) {
			Arrays.fill(distance[i], INF);
		}
		
		for(int x = 0; x < n; x++) {				// 서로 직접 친구인 경우 1로 갱신
			for(int y = x + 1; y < n; y++) {
				if(isLinked(arr[x], arr[y])) distance[x][y] = distance[y][x] = 1;
			}
		}
		
		for(int v = 0; v < n; v++) {				// 연관 친구의 경우 각 배열의 값을 더해가면서 갱신
			for(int s = 0; s < n; s++) {
				for(int e = 0; e < n; e++) {
					if(distance[s][e] > distance[s][v] + distance[v][e]) {
						distance[s][e] = distance[e][s] = distance[s][v] + distance[v][e];
					}
				}
			}
		}
	}
	
	private static boolean isLinked(Line l1, Line l2) {
		return (l1.from >= l2.from && l1.from <= l2.to) || (l2.from >= l1.from && l2.from <= l1.to) ? true: false;
	}
}
