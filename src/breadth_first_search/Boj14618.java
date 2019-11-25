package breadth_first_search;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author exponential-e
 *	백준 14618번: 총깡총깡
 *
 *	@see https://www.acmicpc.net/problem/14618/
 *
 */
public class Boj14618 {
	private static final int INF = 10_000_000;
	private static final String NEW_LINE = "\n";
	
	private static class Node{
		int node;
		int cost;
		
		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int M = in.readInt();
		
		int J = in.readInt() - 1;
		int K = in.readInt();
		
		boolean[] A = new boolean[N];
		for(int i = 0; i < K; i++) {
			A[in.readInt() - 1] = true;
		}
		
		boolean[] B = new boolean[N];
		for(int i = 0; i < K; i++) {
			B[in.readInt() - 1] = true;
		}
		
		ArrayList<Node>[] path = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int cost = in.readInt();
			
			path[from].add(new Node(to, cost));
			path[to].add(new Node(from, cost));
		}
		
		System.out.println(bfs(N, J, path, A, B));
	}
	
	private static String bfs(int n, int j, ArrayList<Node>[] list, boolean[] a, boolean[] b) {	
		int[] visit = new int[n];
		Arrays.fill(visit, INF);
		
		int[] dist = {INF, INF};
		
		Queue<Node> q = new LinkedList<>();
			
		q.offer(new Node(j, 0));
		visit[j] = 0;
			
		while(!q.isEmpty()) {
			Node current = q.poll();
			
			for(Node next: list[current.node]) {
				if(visit[next.node] > visit[current.node] + next.cost) {			// maek Minimum path
					visit[next.node] = visit[current.node] + next.cost;
					
					if(a[next.node] && dist[0] > visit[next.node]) {
						dist[0] = visit[next.node];
					}
					
					else if(b[next.node] && dist[1] > visit[next.node]) {
						dist[1] = visit[next.node];
					}
					
					q.offer(new Node(next.node, visit[next.node]));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		if(dist[0] == dist[1] && dist[0] == INF) return sb.append(-1).toString();
		
		if(dist[0] <= dist[1]) sb.append('A').append(NEW_LINE).append(dist[0]);
		else sb.append('B').append(NEW_LINE).append(dist[1]);
		
		return sb.toString();
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
