package minimumcost_spanning_tree;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 1647번: 도시 분할 계획 by Kruskal
 *
 *	@see https://www.acmicpc.net/problem/1647/
 *
 */
public class Boj1647 {
	private static int[] parent;
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int M = in.readInt();
		
		parent = new int[N + 1];
		for(int i = 1; i < N + 1; i++) {
			parent[i] = i;
		}
		
		PriorityQueue<Node> map = new PriorityQueue<>();
		for(int i = 0; i < M; i++) {
			int from = in.readInt();
			int to = in.readInt();
			int cost = in.readInt();
			
			map.offer(new Node(from, to, cost));
			map.offer(new Node(to, from, cost));
		}
		
		System.out.println(kruskal(N, M, map));		// 결과 출력
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
	
	private static int kruskal(int n, int m, PriorityQueue<Node> pq) {
		int minCost = 0;
		int max = 0;
		
		while(!pq.isEmpty()) {
			Node next = pq.poll();
			
			if(!isCycle(next.node1, next.node2)) {
				minCost += next.cost;
				if(next.cost > max) max = next.cost;		// 최소 비용 간선 중 가장 긴 간선 제거: 마을을 2개로 분할
				
				merge(next.node1, next.node2);
			}
		}
		
		return minCost - max;
	}
	
	private static int find(int from) {
		if(parent[from] == from) return from;
		else return find(parent[from]);
	}
	
	private static void merge(int x, int y) {
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
