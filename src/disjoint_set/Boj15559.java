package disjoint_set;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 15559번: 내 선물을 받아줘!
 *
 *	@see https://www.acmicpc.net/problem/15559/
 *
 */
public class Boj15559 {
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
		
		for(int i = 0; i < parent.length; i++) {		// 집합의 갯수
			if(parent[i] < 0) count++;
		}
		
		System.out.println(count);
	}
	
	private static void init() {
		hm.put('N', new Point(-1, 0));
		hm.put('S', new Point(1, 0));
		hm.put('E', new Point(0, 1));
		hm.put('W', new Point(0, -1));
		
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
		
		if(merge(p, np)) return;
		dfs(m, arr, nextRow, nextCol);
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;					// 집합의 루트, 하나의 루트로 속한 모든 집합을 묶기 위함
		else return parent[x] = find(parent[x]);	// 루트를 재귀로 탐색
	}
	
	private static boolean merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return true;
		
		if(parent[x] > parent[y]) {		// parent[i] 집합의 갯수, i: 그때의 집합의 루트번호
			parent[y] += parent[x];		// 집합 갯수 증가
			parent[x] = y;				// 하위 원소는 루트에 묶어줌
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

