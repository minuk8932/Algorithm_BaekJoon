package strongly_connected_component;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 15783번: 세진 바이러스
 *
 *	@see https://www.acmicpc.net/problem/15783/
 *
 */
public class Boj15783 {
	private static boolean[] isVisited;
	private static ArrayDeque<Integer> stack = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int M = in.readInt();
		
		ArrayList<Integer>[] map = new ArrayList[N];
		ArrayList<Integer>[] revMap = new ArrayList[N];;
		
		for(int i = 0; i < N; i++) {
			map[i] = new ArrayList<>();
			revMap[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int A = in.readInt();
			int B = in.readInt();
			
			map[A].add(B);
			revMap[B].add(A);
		}
		
		isVisited = new boolean[N];
		for(int i = 0; i < N; i++) {
			if(isVisited[i]) continue;
			
			backTracking(map, i, true);		// 탐색하여 반환되는 순으로 스택 저장
			stack.push(i);
		}
		
		isVisited = new boolean[N];
		int scc = 0;
		
		while(!stack.isEmpty()) {
			int start = stack.pop();
			if(isVisited[start]) continue;
			
			backTracking(map, start, false);		// 스택에서 꺼내오면서 탐색
			scc++;								// 탐색의 시작점 갯수
		}
		
		System.out.println(scc);
	}
	
	private static void backTracking(ArrayList<Integer>[] arr, int current, boolean save) {
		if(isVisited[current]) return;
		isVisited[current] = true;
		
		for(int next: arr[current]) {
			if(isVisited[next]) continue;
			
			backTracking(arr, next, save);
			if(save) stack.push(next);
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
