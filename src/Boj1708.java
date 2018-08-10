import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1708번: 블록껍질
 *
 *	@see https://www.acmicpc.net/problem/1708/
 *
 */
public class Boj1708 {
	private static Stack<Point> lifo = null;

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

			if (y < min.y) {
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

		System.out.println(lifo.size());
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

	private static void convexHull(int N, Point[] p) {
		lifo = new Stack<>();
		lifo.push(new Point(p[0].x, p[0].y));
		lifo.push(new Point(p[1].x, p[1].y));

		for (int idx = 2; idx < N; idx++) {
			Point next = new Point(p[idx].x, p[idx].y);

			while (lifo.size() >= 2) {
				Point second = lifo.pop();
				Point first = lifo.peek();

				if (CCW(first, second, next) > 0) {
					lifo.push(second);
					break;
				}
			}

			lifo.push(next);
		}
	}
}
