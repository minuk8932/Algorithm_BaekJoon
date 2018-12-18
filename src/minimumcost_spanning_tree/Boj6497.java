package minimumcost_spanning_tree;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 6497번: 전력난
 *
 *	@see https://www.acmicpc.net/problem/6497/
 *
 */
public class Boj6497 {
	private static final char NEW_LINE = '\n';
	private static int[] parent;
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			int m = in.readInt();
			int n = in.readInt();
			int total = 0;
			
			init(m);
			
			if(m == 0 && n == 0) break;		// 종료 조건
			
			PriorityQueue<Node> map = new PriorityQueue<>();
			for(int i = 0; i < n; i++) {
				int from = in.readInt();
				int to = in.readInt();
				int cost = in.readInt();
				
				map.offer(new Node(from, to, cost));
				map.offer(new Node(to, from, cost));
				total += cost;
			}
			
			sb.append(total - kruskal(map)).append(NEW_LINE);
		}
		
		System.out.println(sb);		// 결과 출력
	}
	
	private static class Node implements Comparable<Node>{
		int node1;
		int node2;
		int cost;
		
		public Node(int node1, int node2, int cost) {
			this.node1 = node1;
			this.node2 = node2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1 : 1;
		}
	}
	
	private static void init(int size) {
		parent = new int[size];
		
		for(int i = 0; i < size; i++) {
			parent[i] = i;
		}
	}
	
	private static int find (int from) {			// 합집합 찾기
		if(parent[from] == from) return from;
		else return find(parent[from]);
	}
	
	private static void merge(int x, int y) {		// 합집합 생성
		x = find(x);
		y = find(y);
		
		if(x < y) parent[y] = x;
		else parent[x] = y;
	}
	
	private static boolean isCycle(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return true;
		else return false;
	}
	
	private static int kruskal(PriorityQueue<Node> pq) {
		int minCost = 0;
		
		while(!pq.isEmpty()) {
			Node next = pq.poll();
			
			if(!isCycle(next.node1, next.node2)) {
				merge(next.node1, next.node2);
				minCost += next.cost;
			}
		}
		
		return minCost;
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
