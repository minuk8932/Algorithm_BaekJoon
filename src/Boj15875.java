import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Boj15875 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;

	private static Point[] values;

	private static int[] parent;
	private static int H, W;
	private static int size;
	
	private static class Point implements Comparable<Point> {
		int idx;
		int val;
		
		public Point(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}

		@Override
		public int compareTo(Point p) {
			return this.val < p.val ? -1: 1;
		}
	}
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		W = in.readInt();
		H = in.readInt();

		size = H * W;

		values = new Point[size];
		parent = new int[size];
		Arrays.fill(parent, -1);

		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				int idx = i * W + j;
				values[idx] = new Point(idx, in.readInt());
			}
		}

		Arrays.sort(values);
		System.out.println(spread());
	}
	
	private static int spread() {
		int max = 0;

		for(int i = 0; i < size; ) {
			int current = i;

			while(values[current].val == values[i].val){
				for(final int[] DIRECTION: DIRECTIONS){
					int nextRow = values[i].idx / W + DIRECTION[ROW];
					int nextCol = values[i].idx % W + DIRECTION[COL];

					if(outOfRange(nextRow, nextCol)) continue;

					int x = find(values[i].idx);
					int y = find(nextRow * W + nextCol);

					if(x == y) continue;
					merge(x, y);
				}

				if(++i == size) break;
			}

			for(int j = current; j < i; j++){
				max = Math.max(max, -parent[find(j)]);
			}
		}

		return max;
	}

	private static boolean outOfRange(int row, int col) {
		return row < 0 || row >= H || col < 0 || col >= W;
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static void merge(int x, int y) {
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}

//	private static void prefix (int N, int end, boolean flag) {
//		int z = find(0);
//
//		for (int i = 0; i < N; i++){
//			Point[] input = new Point[2];
//
//			if(flag){
//				input[0] = new Point(i, 0);
//				input[1] = new Point(i, end);
//			}
//			else{
//				input[0] = new Point(0, i);
//				input[1] = new Point(end, i);
//			}
//
//			q.offer(input[0]);
//			q.offer(input[1]);
//
//			int x = find(input[0].row * W + input[0].col);
//			int y = find(input[1].row * W + input[1].col);
//
//			if(x != z) merge(x, z);
//			if(y != z) merge(y, z);
//		}
//	}
	
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
