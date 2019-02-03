package disjoint_set;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 2162번: 선분 그룹
 *
 *	@see https://www.acmicpc.net/problem/2162/
 *
 */
public class Boj2162 {
	private static int[] parent;
	private static final String NEW_LINE = "\n";
	
	private static class Line{
		long x1;
		long y1;
		long x2;
		long y2;
		
		public Line(long x1, long y1, long x2, long y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		StringBuilder sb = new StringBuilder();
		int N = in.readInt();
		
		Line[] line = new Line[N];
		init(N);
			
		for(int i = 0; i < N; i++) {
			line[i] = new Line(in.readLong(), in.readLong(), in.readLong(), in.readLong());
		}
			
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i == j) continue;
				int l1 = find(i);
				int l2 = find(j);
				
				if(l1 == l2) continue;
				if(getIntersectCount(line[i], line[j])){		// 서로 그룹이면
					merge(i, j);								// 집합
				}
			}
		}
		
		int grpCnt = 0, max = 0;
		
		for(int i = 0; i < N; i++) {
			if(parent[i] < 0) {
				grpCnt++;								// 존재하는 집합의 갯수
				if(max < -parent[i]) max = -parent[i];	// 집합 중 최대 크기
			}
		}
		
		sb.append(grpCnt).append(NEW_LINE).append(max);
		System.out.print(sb);
	}
	
	private static void init(int n) {
		parent = new int[n];
		
		for(int i = 0; i < n; i++) {
			parent[i] = -1;
		}
	}
	
	private static boolean getIntersectCount(Line arr1, Line arr2) {
		int[] outer = {outerProduct(arr1.x1, arr1.y1, arr1.x2, arr1.y2, arr2.x1, arr2.y1)		// 외적 값
				, outerProduct(arr1.x1, arr1.y1, arr1.x2, arr1.y2, arr2.x2, arr2.y2)
				, outerProduct(arr2.x1, arr2.y1, arr2.x2, arr2.y2, arr1.x1, arr1.y1)
				, outerProduct(arr2.x1, arr2.y1, arr2.x2, arr2.y2, arr1.x2, arr1.y2)};
		
		int prev = outer[0] * outer[1];
		int post = outer[2] * outer[3];
		
		if(prev == 0 && post == 0) {
			if (Math.max(arr1.x1, arr1.x2) < Math.min(arr2.x1, arr2.x2)) return false;		// 셋다 같은 직선상에 존재: 범위 내에 속하는가
			if (Math.min(arr1.x1, arr1.x2) > Math.max(arr2.x1, arr2.x2)) return false;
			if (Math.max(arr1.y1, arr1.y2) < Math.min(arr2.y1, arr2.y2)) return false;
			if (Math.min(arr1.y1, arr1.y2) > Math.max(arr2.y1, arr2.y2)) return false;
			
			return true;
		}
		
		return prev <= 0 && post <= 0;
	}
	
	private static int outerProduct(long x1, long y1, long x2, long y2, long x3, long y3) {
		long ccw = (x1 * y2 + x2 * y3 + x3 * y1) - (y1 * x2 + y2 * x3 + y3 * x1);
		
		if(ccw > 0) return 1;
		else if(ccw < 0) return -1;
		else return 0;
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
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
		
		public long readLong() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;
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
