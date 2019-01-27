package depth_first_search;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 16724번: 피리 부는 사나이
 *
 *	@see https://acmicpc.net/problem/16724/
 *
 */
public class Boj16724 {
	private static int count;
	private static boolean critical;
	private static boolean[][] isVisited;
	
	private static ArrayList<Point> pair = new ArrayList<>();
	
	private static final HashMap<Character, Point> hm = new HashMap<>();
	
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
				if(!pair.isEmpty()) pair = new ArrayList<>();		// 집합 쌍 저장소 생성
				
				critical = false;
				dfs(M, map, i, j);
			}
		}
		
		System.out.println(count);
	}
	
	private static void init() {
		hm.put('U', new Point(-1, 0));
		hm.put('D', new Point(1, 0));
		hm.put('R', new Point(0, 1));
		hm.put('L', new Point(0, -1));
	}
	
	private static void dfs(int m, char[][] arr, int row, int col) {
		if(isVisited[row][col] || critical) return;
		isVisited[row][col] = true;
		
		Point current = hm.get(arr[row][col]);
		
		pair.add(new Point(row ,col));
		
		int nextRow = row + current.row;
		int nextCol = col + current.col;
		
		if(isCycle(nextRow, nextCol)) {		// 해당 쌍이 리스트에 존재하는 경우: 하나의 집합
			critical = true;
			count++;				// 집합 갯수 +1
			
			return;
		}
		
		dfs(m, arr, nextRow, nextCol);
	}
	
	private static boolean isCycle(int row, int col) {
		for(Point p: pair) {
			if(row == p.row && col == p.col) return true;
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

