package disjoint_set;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 16724번: 피리 부는 사나이
 *
 *	@see https://www.acmicpc.net/problem/16724/
 *
 */
public class Boj16724 {
	private static int[] parent;
	private static boolean[][] isVisited;
	
	private static HashMap<Character, Point> hm = new HashMap<>();
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int M = in.readInt();
		
		parent = new int[N * M];
		isVisited = new boolean[N][M];
		init();
		
		char[][] map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String line = in.readString();
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(isVisited[i][j]) continue;
				dfs(M, map, i, j);
			}
		}
		
		int count = 0;		
		
		for(int i = 0; i < parent.length; i++) {		// 집합 갯수
			if(parent[i] < 0) count++;
		}
		
		System.out.println(count);
	}
	
	private static void init() {
		hm.put('U', new Point(-1, 0));
		hm.put('D', new Point(1, 0));
		hm.put('R', new Point(0, 1));
		hm.put('L', new Point(0, -1));
		
		Arrays.fill(parent, -1);
	}
	
	private static void dfs(int m, char[][] arr, int row, int col) {
		if(isVisited[row][col]) return;
		isVisited[row][col] = true;
		
		Point direct = hm.get(arr[row][col]);
		
		int nextRow = row + direct.row;
		int nextCol = col + direct.col;
		
		int p = row * m + col;
		int np = nextRow * m + nextCol;
		
		if(merge(p, np)) return;			// 집합 생성, 이미 집합이면 함수 반환
		dfs(m, arr, nextRow, nextCol);
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static boolean merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return true;
		
		if(parent[x] > parent[y]) {
			parent[y] += parent[x];
			parent[x] = y;
		}
		else {
			parent[x] += parent[y];
			parent[y] = x;
		}
		
		return false;
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

		public String readString() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
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

