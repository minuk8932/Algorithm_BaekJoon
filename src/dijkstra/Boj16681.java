package dijkstra;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 16681번: 등산
 *
 *	@see https://www.acmicpc.net/problem/16681/
 *
 */
public class Boj16681 {
	private static final String NO_WAY = "Impossible";
	private static final long INF = Long.MAX_VALUE;
	private static final long UNINF = Long.MIN_VALUE;
	
	private static long[][] cost;
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int M = in.readInt();
		int D = in.readInt();
		int E = in.readInt();
		
		int[] height = new int[N + 1];
		cost = new long[2][N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			height[i] = in.readInt();
		}
		
		ArrayList<Node>[] hikeRoute = new ArrayList[N + 1];
		for(int i = 1; i < N + 1; i++) {
			hikeRoute[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {			
			int a = in.readInt();
			int b = in.readInt();
			int n = in.readInt();
			
			hikeRoute[a].add(new Node(b, n));
			hikeRoute[b].add(new Node(a, n));
		}
		
		dijkstra(hikeRoute, N, M, height, 1, 0);		// 1에서 출발
		dijkstra(hikeRoute, N, M, height, N, 1);		// N에서 출발
		
		long res = getResult(height, E, D, N);
		System.out.println(res == UNINF ? NO_WAY: res);		// 결과 출력
	}
	
	private static class Node implements Comparable<Node>{
		int node;
		long cost;
		
		public Node(int node, long cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			if(this.cost < n.cost) return -1;
			else if(this.cost > n.cost) return 1;
			else return 0;
		}
	}
	
	private static void dijkstra(ArrayList<Node>[] map, int n, int m, int[] h, int start, int idx) {	
		Arrays.fill(cost[idx], INF);
		cost[idx][start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, cost[idx][start]));
			
		while(!pq.isEmpty()) {
			Node current = pq.poll();
				
			if (cost[idx][current.node] < current.cost) continue;
				
			for(Node next: map[current.node]) {
				if(h[current.node] >= h[next.node]) continue;
					
				if(cost[idx][next.node] > cost[idx][current.node] + next.cost) {
					cost[idx][next.node] = cost[idx][current.node] + next.cost;
						
					pq.offer(new Node(next.node, cost[idx][next.node]));
				}
			}
		}
	}
	
	private static long getResult(int[] h, int e, int d, int n) {
		long max = UNINF;
		
		for(int i = 1; i <  n + 1; i++) {
			long sum = h[i] * e - (cost[0][i] + cost[1][i]) * d;			// 성취감 계산
			
			if(isAlright(cost[0][i]) && isAlright(cost[1][i]) && max < sum) {
				max = sum;
			}
		}
		
		return max;
	}
	
	private static boolean isAlright(long c) {
		if(c > 0 && c < INF) return true;
		else return false;
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
