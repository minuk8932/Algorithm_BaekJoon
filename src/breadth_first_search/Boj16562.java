package breadth_first_search;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 16562번: 친구비
 *
 *	@see https://www.acmicpc.net/problem/16562/
 *
 */
public class Boj16562 {
	private static final int INF = 10_000_001;
	private static final String NO_FRIENDS = "Oh no";
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int M = in.readInt();
		int k = in.readInt();
		
		int[] fList = new int[N + 1];
		for(int i = 1; i < N + 1; i++) {
			fList[i] = in.readInt();
		}
		
		ArrayList<Integer>[] relation = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			relation[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			int from = in.readInt();
			int to = in.readInt();
			
			relation[from].add(to);
			relation[to].add(from);
		}
		
		System.out.println(getMinimumCost(N, M, k, relation, fList));		// 결과 출력
	}
	
	private static StringBuilder getMinimumCost(int n, int m, int k, ArrayList<Integer>[] graph, int[] fArr) {
		StringBuilder sb = new StringBuilder();
		
		int res = 0;
		boolean[] isVisited = new boolean[n + 1];
		Queue<Integer> q = new LinkedList<>();
		
		for(int start = 1; start < n + 1; start++) {
			if(isVisited[start]) continue;				// 이미 친구거나 친구의 친구인 경우
			int min = INF;
			
			if(fArr[start] < min) min = fArr[start];
			q.offer(start);
			isVisited[start] = true;
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next: graph[current]) {
					if(isVisited[next]) continue;
					isVisited[next] = true;
					
					if(fArr[next] < min) min = fArr[next];		// 돌고 돈 친구 중 가장 저렴한 비용을 저장
					
					q.offer(next);
				}
			}
			
			res += min;
			if(res > k) break;		// 비용이 모자란 경우
		}
		
		sb.append(res <= k ? res : NO_FRIENDS);
		return sb;
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

		public double readDouble() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			double res = 0;
			while (!isSpaceChar(c) && c != '.') {
				if (c == 'e' || c == 'E') {
					return res * Math.pow(10, readInt());
				}
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			}
			if (c == '.') {
				c = read();
				double m = 1;
				while (!isSpaceChar(c)) {
					if (c == 'e' || c == 'E') {
						return res * Math.pow(10, readInt());
					}
					if (c < '0' || c > '9') {
						throw new InputMismatchException();
					}
					m /= 10;
					res += (c - '0') * m;
					c = read();
				}
			}
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

		public String next() {
			return readString();
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}
