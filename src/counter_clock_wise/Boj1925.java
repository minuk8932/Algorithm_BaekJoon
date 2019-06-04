package counter_clock_wise;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1925번: 삼각형
 *
 *	@see https://www.acmicpc.net/problem/1925/
 *
 */
public class Boj1925 {
	private static final String NOT_T = "X";
	
	private static final String JJJ_T = "JungTriangle";
	private static final String[] T_TYPE = {"Dunkak2Triangle", "Jikkak2Triangle", "Yeahkak2Triangle",
			"DunkakTriangle", "JikkakTriangle", "YeahkakTriangle"};
	
	private static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Point[] p = new Point[3];
		
		for(int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			p[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(triangle(p));
	}
	
	private static String triangle(Point[] arr) {
		if(ccw(arr)) return NOT_T;
		
		int[] dist = new int[3];
		
		int max = 0, min = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i++) {
			dist[i] = euclideDistancePow(arr[i], arr[(i + 1) % 3]);
			
			if(dist[i] > max) max = dist[i];
			if(dist[i] < min) min = dist[i];
		}
		
		if(max == min) return JJJ_T;
		
		return T_TYPE[getType(dist, max)];
	}
	
	private static boolean ccw(Point[] arr) {									// CCW 알고리즘
		return (arr[0].x * arr[1].y + arr[1].x * arr[2].y + arr[2].x * arr[0].y)
				- (arr[1].x * arr[0].y + arr[2].x * arr[1].y + arr[0].x * arr[2].y) == 0 ? true: false;
	}
	
	private static int euclideDistancePow(Point p1, Point p2) {					// 두 점 사이 거리
		return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
	}
	
	private static int getType(int[] d, int m) {
		int value = -1;
		
		for(int i = 0; i < 3; i++) {
			if(d[i] == d[(i + 1) % 3]) value = d[i];
		}
		
		int sum = 0;
		
		if(value != -1) {				// 이등변인 경우
			int other = 0;
			sum = value + value;
			
			for(int i = 0; i < 3; i++) {
				if(value != d[i]) other = d[i];
			}
			
			return sum == other ? 1 : sum < other ? 0: 2;		// 가장 큰 선분과 길이가 같으면 직각, 큰 선분 보다 크면 예각, 작으면 둔각
		}
		else {
			for(int i = 0; i < 3; i++) {
				if(d[i] != m) sum += d[i];
			}
			
			return sum == m ? 4 : sum < m ? 3: 5;
		}
	}
}
