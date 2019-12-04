package convex_hull;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;

/**
 * 
 * 	@author exponential-e
 *	백준 17403번: 가장 높고 넓은 성
 *
 *	@see https://www.acmicpc.net/problem/17403/
 *
 */
public class Boj17403 {
	private static final int INF = 1_000_001;
	private static final String SPACE = " ";
	
	private static Point min;
	private static boolean[] used;
	
	private static class Point {
		int idx;
		long x;
		long y;

		public Point(int idx, long x, long y) {
			this.idx = idx;
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
				
				else {
					long a = distancePow(min, p1);
					long b = distancePow(min, p2);
					
					if (a < b) return -1;
					else if (a > b) return 1;
					else return 0;
				}
			}
		};
	}

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		
		Point[] pos = new Point[N];
		used = new boolean[N];

		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();

			pos[i] = new Point(i, x, y);
		}
		
		convexHull(N, pos);
	}
	
	private static long CCW(Point v1, Point v2, Point v3) {
		return (v1.x * v2.y + v2.x * v3.y + v3.x * v1.y) - (v3.x * v2.y + v2.x * v1.y + v1.x * v3.y);
	}
	
	private static long distancePow(Point p1, Point p2) {
		return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
	}
	
	private static void convexHull(int N, Point[] p) {
		int[] result = new int[N];
		int loop = 1;
		
		while(true) {
			min = new Point(-1, INF, INF);
			
			ArrayList<Point> pIdx = new ArrayList<>();
			ArrayDeque<Point> stack = new ArrayDeque<>();
			
			for(int a = 0; a < N; a++) {							// add point not used
				if(used[p[a].idx]) continue;
				
				if (p[a].y < min.y) {								// find min point
					min = new Point(p[a].idx, p[a].x, p[a].y);
				}

				else if (p[a].y == min.y) {
					if (p[a].x < min.x) {
						min = new Point(p[a].idx, p[a].x, p[a].y);
					}
				}
				
				pIdx.add(p[a]);
			}
			
			int leng = pIdx.size();
			if(leng <= 2) break;											// cannot make
			
			Collections.sort(pIdx, Point.comparator);
			
			stack.push(pIdx.get(0));
			stack.push(pIdx.get(1));
			
			for (int idx = 2; idx < leng; idx++) {							// make convex hull not used
				Point next = pIdx.get(idx);
	
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
			
			if(stack.size() <= 2) break;								// cannot make
			
			while(!stack.isEmpty()) {
				Point current = stack.pop();

				result[current.idx] = loop; 
				used[current.idx] = true;
			}
			
			loop++;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(result[i]).append(SPACE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}

		public int readInt() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
		
		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}
