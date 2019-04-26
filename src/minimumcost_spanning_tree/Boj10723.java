package minimumcost_spanning_tree;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 10723번: 판게아1
 *
 *	@see https://www.acmicpc.net/problem/10723/
 *
 */
public class Boj10723 {
	private static final String NEW_LINE = "\n";
	private static int[] parent;
	
	private static ArrayList<Node> fix;
	
	private static class Node implements Comparable<Node>{
		int vertex1;
		int vertex2;
		int cost;
		
		public Node(int vertex1, int vertex2, int cost) {
			this.vertex1 = vertex1;
			this.vertex2 = vertex2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		StringBuilder sb = new StringBuilder();
		int T = in.readInt();
		
		while(T-- > 0) {
			int n = in.readInt();
			int m = in.readInt();
			
			fix = new ArrayList<>();
			init(n);
			
			for(int i = 1; i < n; i++) {
				fix.add(new Node(i, in.readInt(), in.readInt()));
			}
			
			long result = 0;
			while(m-- > 0) {
				fix.add(new Node(in.readInt(), in.readInt(), in.readInt()));		// 도로 하나씩 추가
				
				result ^= mst();			// 결과 xor
				Arrays.fill(parent, -1);
			}
			
			sb.append(result).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static void init(int n) {
		parent = new int[n];
		for(int i = 0; i < n; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static boolean merge(int x, int y) {
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
	
	private static long mst() {
		long min = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(Node n: fix) {
			pq.offer(n);
		}
		
		while(!pq.isEmpty()) {				// kruskal
			Node current = pq.poll();
			
			if(merge(current.vertex1, current.vertex2)) continue;
			min += current.cost;
		}

		return min;
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
