package counter_clock_wise;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12871번: PIZZA ALVOLOC
 *
 *	@see https://www.acmicpc.net/problem/12871/
 *
 */
public class Boj12871 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Point[] p = new Point[4];
		for(int i = 0; i < p.length; i++) {
			p[i] = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(isDivide(p));		// 결과 출력
	}
	
	private static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static int isDivide(Point[] arr) {
		int value = CCW(arr[0], arr[1], arr[2]);		// 서로 다른 부호의 결과인 경우 교차
		value *= CCW(arr[0], arr[1], arr[3]);
		
		return value < 0 ? 1 : 0;
	}
	
	private static int CCW(Point p1, Point p2, Point p3) {
		int res = (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
		return res == 0 ? 0 : res > 0 ? 1 : -1;
	}
}
