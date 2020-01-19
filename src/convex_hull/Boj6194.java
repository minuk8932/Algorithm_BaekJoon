package convex_hull;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 6194번: Building the moat
 *
 *	@see https://www.acmicpc.net/problem/6194/
 *
 */
public class Boj6194 {
	private static ArrayDeque<Point> stack = null;

	private static final int INF = 40_001;
	private static Point min = new Point(INF, INF);

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Point[] pos = new Point[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if (y < min.y) {				// find minimum
				min = new Point(x, y);
			}

			else if (y == min.y) {
				if (x < min.x) {
					min = new Point(x, y);
				}
			}

			pos[i] = new Point(x, y);
		}

		Arrays.sort(pos, Point.comparator);
		convexHull(N, pos);
		
		System.out.println(getArea());
	}
	
	private static class Point {
		long x;
		long y;

		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
		
		private static Comparator<Point> comparator = new Comparator<Point>() {

			@Override
			public int compare(Point p1, Point p2) {
				long c = CCW(min, p1, p2);
	
				if (c > 0) {
					return -1;
				}
				
				else if (c < 0) {
					return 1;
				}
				
				else if (c == 0) {
					long a = distancePow(min, p1);
					long b = distancePow(min, p2);
					
					if (a < b) return -1;
					else if (a > b) return 1;
					else if (a == b) return 0;
				}
				
				return 0;
			}
		};
	}
	
	private static long CCW(Point v1, Point v2, Point v3) {
		return (v1.x * v2.y + v2.x * v3.y + v3.x * v1.y) - (v3.x * v2.y + v2.x * v1.y + v1.x * v3.y);
	}
	
	private static long distancePow(Point p1, Point p2) {
		return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
	}
	
	private static void convexHull(int N, Point[] p) {		// making convexhull
		stack = new ArrayDeque<>();
		stack.push(new Point(p[0].x, p[0].y));
		stack.push(new Point(p[1].x, p[1].y));

		for (int idx = 2; idx < N; idx++) {
			Point next = new Point(p[idx].x, p[idx].y);

			while (stack.size() >= 2) {
				Point second = stack.pop();
				Point first = stack.peek();

				if (CCW(first, second, next) > 0) {
					stack.push(second);
					break;
				}
			}

			stack.push(next);
		}
	}
	
	private static String getArea(){
		double res = 0;
		Point first = stack.pop();
		Point remain = first;
		
		while(!stack.isEmpty()) {
			Point p = stack.pop();
			res += Math.sqrt((double) distancePow(first, p));		// get Distance each point
			
			first = p;
		}
		
		res += Math.sqrt((double) distancePow(first, remain));
		
		return String.format("%.2f", res);
	}
}
