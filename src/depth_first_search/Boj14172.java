package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14172번: Moo Cast
 *
 *	@see https://www.acmicpc.net/problem/14172/
 *
 */
public class Boj14172 {
	private static boolean[][] path;
	
	private static class Coordinate{
		int x;
		int y;
		int range;
		
		public Coordinate(int x, int y, int range) {
			this.x = x;
			this.y = y;
			this.range = range;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Coordinate[] cow = new Coordinate[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cow[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		makePath(N, cow);
		
		int result = 0;
		for(int i = 0; i < N; i++) {
			boolean[] visit = new boolean[N];
			result = Math.max(dfs(N, visit, i), result);	// 전달 가능한 최대 수
		}
		
		System.out.println(result);
	}
	
	private static void makePath(int n, Coordinate[] c) {
		path = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!distance(c[i], c[j])) continue;		// 레이더 거리 내에 있으면, 경로 생성
				path[i][j] = true;
			}
		}
	}
	
	private static boolean distance(Coordinate cow1, Coordinate cow2) {
		int dist = (cow1.x - cow2.x) * (cow1.x - cow2.x) + (cow1.y - cow2.y) * (cow1.y - cow2.y);
		return dist <= cow1.range * cow1.range ? true : false;
	}
	
	private static int dfs(int n, boolean[] visit, int current) {	
		if(visit[current]) return 0;
		visit[current] = true;
		
		int count = 1;
		for(int next = 0; next < n; next++) {
			if(!path[current][next]) continue;
			count += dfs(n, visit, next);
		}
		
		return count;
	}
}
