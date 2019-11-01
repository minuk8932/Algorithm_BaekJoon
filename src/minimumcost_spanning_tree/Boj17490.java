package minimumcost_spanning_tree;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

/**
 * 
 * 	@author exponential-e
 *	백준 17490번: 일감호에 다리 놓기
 *
 *	@see https://www.acmicpc.net/problem/17490/
 *
 */
public class Boj17490 {
	private static final long INF = Long.MAX_VALUE;
	private static int[] parent;
	private static ArrayList<Integer>[] linked;
	
	private static class Path implements Comparable<Path>{
		int node1;
		int node2;
		long cost;
		
		public Path(int node1, int node2, long cost) {
			this.node1 = node1;
			this.node2 = node2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Path p) {
			return this.cost < p.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int M = in.readInt();
		long K = in.readLong();
		
		init(N);
		
		PriorityQueue<Path> spot = new PriorityQueue<>();
		linked = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			linked[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++) {
			spot.offer(new Path(i, N, in.readInt()));
		}
		
		while(M-- > 0) {
			int node1 = in.readInt() - 1;
			int node2 = in.readInt() - 1;
			
			spot.offer(new Path(node1, node2, INF));
			linked[node1].add(node2);
			linked[node2].add(node1);
		}
		
		for(int i = 0; i < N; i++) {						// make already path
			int size = linked[i].size();
			if(size == 2) continue;
			
			int next = (i + N + 1) % N;
			int prev = (i - 1 + N) % N;
			
			if(size == 0) {
				spot.offer(new Path(i, next, 0));
				spot.offer(new Path(i, prev, 0));
			}
			else {
				if (linked[i].get(0) == next) spot.offer(new Path(i, prev, 0));
				else spot.offer(new Path(i, next, 0));
			}
		}
		
		System.out.println(kruskal(N, K, spot));
	}
	
	private static void init(int n) {
		parent = new int[n + 1];
		
		for(int i = 0; i < n + 1; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static boolean merged(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return true;
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
		
		return false;
	}
	
	private static String kruskal(int n, long k, PriorityQueue<Path> pq) {
		long cost = 0;
		
		while(!pq.isEmpty()) {
			Path current = pq.poll();
			
			if(merged(current.node1, current.node2)) continue;
			if(-parent[find(0)] == n && parent[find(n)] == -1) break;			// link all structure except wow island
			
			cost += current.cost;
		}
		
		return cost <= k && -parent[find(0)] >= n ? "YES": "NO";				// less cost but, not all linked => 'NO'
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
