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
 *	백준 5214번: 환승
 *
 *	@see https://www.acmicpc.net/problem/5214/
 *
 */
public class Boj5214 {
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int K = in.readInt();
		int M = in.readInt();
		
		ArrayList<Integer>[] hyperTube = new ArrayList[N + M + 1];		// M개의 그룹 만큼 가상 노드를 추가
		for(int i = 0; i < N + M + 1; i++) {
			hyperTube[i] = new ArrayList<>();
		}
		
		for(int imagine = N + 1; imagine < N + M + 1; imagine++) {		// 가상 노드를 통해 실제 노드 그룹화	
			for(int i = 0; i < K; i++) {
				int real = in.readInt();
				
				hyperTube[real].add(imagine);
				hyperTube[imagine].add(real);
			}
		}
		
		System.out.println(bfs(N, M, hyperTube));
	}
	
	private static int bfs(int n, int m, ArrayList<Integer>[] graph) {
		int[] isVisited = new int[n + m + 1];
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		
		isVisited[1] = 1;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: graph[current]) {
				if(isVisited[next] == 0) {
					isVisited[next] = isVisited[current] + 1;		// 거리 측정
						
					q.offer(next);
				}
			}
		}
		
		return isVisited[n] == 0 ? -1 : isVisited[n] / 2 + 1;		// 노드 갯수가 2배 정도 증가
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
