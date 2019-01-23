package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1993번: 경주
 *
 *	@see https://www.acmicpc.net/problem/1993/
 *
 */
public class Boj1993 {
	private static final String INNER_BREAK = "# 0";
	private static final String RACE = "Race ";
	private static final String NEW_LINE = "\n";
	private static final String COLON = ": ";
	
	private static int res = 0;
	private static boolean[] isVisited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int race = 1;
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			Coordinate[] coor = new Coordinate[N + 2];
			coor[0] = new Coordinate(0, 0, 0);
			
			for(int i = 1; i < N + 1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int score = Integer.parseInt(st.nextToken());
				
				coor[i] = new Coordinate(x, y, score);
			}
			
			coor[N + 1] = new Coordinate(0, 0, 0);
			sb.append(RACE).append(race++).append(NEW_LINE);

			while(true) {
				String line = br.readLine();
				if(line.equals(INNER_BREAK)) break;
				
				StringTokenizer st = new StringTokenizer(line);
				String name = st.nextToken();
				int limit = Integer.parseInt(st.nextToken());
				
				isVisited = new boolean[N + 2];

				backTracking(N, coor, limit, 0, 0, 0);
				sb.append(name).append(COLON).append(res).append(NEW_LINE);
				
				res = 0;
			}
		}
		
		System.out.println(sb);
	}
	
	private static class Coordinate{
		int x;
		int y;
		int score;
		
		public Coordinate(int x, int y, int score) {
			this.x = x;
			this.y = y;
			this.score = score;
		}
	}
	
	private static void backTracking(int n, Coordinate[] c, int limit, int start, double dist, int score) {
		if(limit < dist) return;
		if(start == n + 1) {
			res = Math.max(res, score);
			return;
		}
		
		for(int next = start + 1; next < n + 2; next++) {			
			if(isVisited[next]) continue;
			isVisited[next] = true;
			
			double nextDist = getDistance(start, next, c);			// i ~ i + 1 거리
			backTracking(n, c, limit, next, dist + nextDist, score + c[next].score);	// i + 1까지 거리 점수 증가 재귀
			isVisited[next] = false;			// 백트래킹
		}
	}
	
	private static double getDistance(int p1, int p2, Coordinate[] c) {
		return Math.sqrt(Math.pow(c[p2].x - c[p1].x, 2) + Math.pow(c[p2].y - c[p1].y, 2));
	}
}
