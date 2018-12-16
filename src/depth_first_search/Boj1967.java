package depth_first_search;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 1967번: 트리의 지름
 *
 *	@see https://www.acmicpc.net/problem/1967/
 *
 */
public class Boj1967 {
	private static boolean[] isVisited;
	private static ArrayList<Node>[] tree;
	
	private static Node node = new Node(-1, -1);

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
		int n = in.readInt();

		tree = new ArrayList[n + 1];
		for (int i = 1; i < n + 1; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 1; i < n; i++) {
			int parent = in.readInt();
			int son = in.readInt();
			int weight = in.readInt();

			tree[parent].add(new Node(son, weight));
			tree[son].add(new Node(parent, weight));
		}

		isVisited = new boolean[n + 1];
		dfs(new Node(1, 0));				// 시작점 찾기
		
		isVisited = new boolean[n + 1];
		dfs(new Node(node.edge, 0));
		
		System.out.println(node.cost);
	}
	
	private static class Node{
		int edge;
		int cost;
		
		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;
		}
	}
	
	private static void dfs(Node current) {
		isVisited[current.edge] = true;
		
		for(Node next: tree[current.edge]) {
			if(isVisited[next.edge]) continue;
			int sum = next.cost + current.cost;
			
			if(node.cost < sum) {			// 시작 노드에서 가장 멀리 떨어진 노드가 시작노드가 될 수 있다.
				node.cost = sum;			// 시작 노드를 구하면 거기서 가장 멀리 떨어진 노드까지의 거리가 지름
				node.edge = next.edge;
			}
			
			dfs(new Node(next.edge, sum));
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
