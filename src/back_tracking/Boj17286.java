package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17286번: 유미
 *
 *	@see https://www.acmicpc.net/problem/17286/
 *
 */
public class Boj17286 {
	private static double min = 1_000_000;
	private static boolean[] visit = new boolean[4];
	
	private static class Point{
		double x;
		double y;
		
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Point[] p = new Point[4];
		for(int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			p[i] = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}
				
		backTracking(p, 0, 0, 0);
		System.out.println((int) min);
	}
	
	private static void backTracking(Point[] arr, int current, int count, double distance) {		
		if(count == 3) {						// 모두 만났을 때 최단 거리
			min = Math.min(distance, min);
			return;
		}

		for(int next = 1; next < 4; next++) {
			if(visit[next]) continue;
			visit[next] = true;

			backTracking(arr, next, count + 1, distance + getDistance(arr[current], arr[next]));	// 사람 사이의 거리를 구해가며 백트래킹
			visit[next] = false;
		}
	}
	
	private static double getDistance(Point from, Point to) {
		return Math.sqrt(Math.pow(from.x - to.x, 2) + Math.pow(from.y - to.y, 2));
	}
}
