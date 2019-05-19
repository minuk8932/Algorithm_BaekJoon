package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17903번: Total Circle
 *
 *	@see https://www.acmicpc.net/problem/17903/
 *
 */
public class Boj17903 {
	
	private static class Coordinate{
		int x;
		int y;
		
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Coordinate[] P = new Coordinate[N];
		Coordinate[] Q = new Coordinate[M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			P[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			Q[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(getMax(N, M, P, Q));
	}
	
	private static long getMax(int n, int m, Coordinate[] p, Coordinate[] q) {
		long max = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				long distance = getEuclideanDistance(p[i], q[j]);			// 유클리드 거리
				if(distance > max) max = distance;
			}
		}
		
		return max;
	}
	
	private static long getEuclideanDistance(Coordinate p, Coordinate q) {
		return (long) Math.pow(p.x - q.x, 2) + (long) Math.pow(p.y - q.y, 2);
	}
}
