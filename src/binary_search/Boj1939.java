package binary_search;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 1939번: 중량 제한
 *
 *	@see https://www.acmicpc.net/problem/1939/
 *
 */
public class Boj1939 {
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int M = in.readInt();
		int max = 0;
		
		ArrayList<Node>[] map = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			map[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			int from = in.readInt();
			int to = in.readInt();
			int cost = in.readInt();
			
			map[from].add(new Node(to, cost));
			map[to].add(new Node(from, cost));
			
			if(cost > max) max = cost;				// 이동 가능한 최대 중량
		}
		
		int start = in.readInt();
		int end = in.readInt();
		
		System.out.println(binarySearch(map, start ,end, max, N)); 	// 결과 출력
	}
	
	private static class Node{
		int edge;
		int cost;
		
		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;
		}
	}
	
	private static int binarySearch(ArrayList<Node>[] list, int s, int e, int end, int n) {
		int start = 0, res = 0;
		
		while(start <= end){
			int mid = (start + end) / 2;
			
			boolean pass = bfs(list, mid, n, s, e);

			if(pass) {						// 가능한 경우, 더 큰 중량도 이동가능한지 탐색
				start = mid + 1;
				res = Math.max(mid, res);
			}
			else {					// 불가능한 경우
				end = mid - 1;
			}
		}
		
		return res;
	}
	
	private static boolean bfs(ArrayList<Node>[] list, int limit, int n, int s, int e) {
		boolean[] isVisited = new boolean[n + 1];
			
		isVisited[s] = true;
		Queue<Integer> q = new LinkedList<>();
		q.offer(s);
				
		while(!q.isEmpty()) {
			int current = q.poll();
					
			for(Node next: list[current]) {
				if(next.cost < limit) continue;		// 중량이 커서 다리가 무너지는 경우
				if(isVisited[next.edge]) continue;
				isVisited[next.edge] = true;
					
				if(next.edge == e) return true;		// 통과
				q.offer(next.edge);
			}
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
