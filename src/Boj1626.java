import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.PriorityQueue;


public class Boj1626 {
	private static PriorityQueue<Path> pq = new PriorityQueue<>();
	private static PriorityQueue<Path> nontouch = new PriorityQueue<>();

	private static ArrayList<Node>[] tree;
	private static int[][] parent;
	private static int[] deep;
	private static boolean[] visit;
	private static int[] set;

	private static int V;

	private static class Node {
		int node;
		int cost;

		public Node(int node, int cost){
			this.node = node;
			this.cost = cost;
		}
	}

	private static class Path implements Comparable<Path>{
		int from;
		int to;
		int cost;

		public Path(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Path p) {
			return this.cost < p.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		V = in.readInt();
		int E = in.readInt();

		init(V);
		
		while(E-- > 0) {
			int x = in.readInt() - 1;
			int y = in.readInt() - 1;
			int cost = in.readInt();
			
			pq.offer(new Path(x, y, cost));
		}

		MST();
		dfs(0, 0);
		connecting();

		System.out.println(getResult());
	}
	
	private static void init(int N) {
		tree = new ArrayList[N];
		parent = new int[N][21];
		deep = new int[N];
		set = new int[N];
		visit = new boolean[N];

		for(int i = 0; i < N; i++) {
			tree[i] = new ArrayList<>();
			set[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(set[x] < 0) return x;
		return set[x] = find(set[x]);
	}
	
	private static boolean merged(int x, int y) {
		x = find(x);
		y = find(y);

		if(x == y) return true;
		
		if(set[x] < set[y]){
			set[x] += set[y];
			set[y] = x;
		}
		else{
			set[y] += set[x];
			set[x] = y;
		}

		return false;
	}
	
	private static int MST() {
		int minCost = 0;
		
		while(!pq.isEmpty()) {
			Path next = pq.poll();
			if(merged(next.from, next.to)){
				nontouch.offer(next);
				continue;
			}

			tree[next.from].add(new Node(next.to, next.cost));
			tree[next.to].add(new Node(next.from, next.cost));
			minCost += next.cost;
		}
		
		return minCost;
	}

	private static void dfs(int current, int depth){
		deep[current] = depth;
		visit[current] = true;

		for(Node next: tree[current]){
			if(visit[next.node]) continue;

			parent[next.node][0] = current;
			dfs(next.node, depth + 1);
		}
	}

	private static void connecting(){
		for(int p = 1; p < 21; p++){
			for(int cur = 0; cur < V; cur++){
				parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
			}
		}
	}

	private static int getResult(){
		for(int i = 1; i < V; i++){
			if(deep[i] == 0) return -1;
		}




		return 0;
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

		public String readString() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public double readDouble() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			double res = 0;
			while (!isSpaceChar(c) && c != '.') {
				if (c == 'e' || c == 'E') {
					return res * Math.pow(10, readInt());
				}
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			}
			if (c == '.') {
				c = read();
				double m = 1;
				while (!isSpaceChar(c)) {
					if (c == 'e' || c == 'E') {
						return res * Math.pow(10, readInt());
					}
					if (c < '0' || c > '9') {
						throw new InputMismatchException();
					}
					m /= 10;
					res += (c - '0') * m;
					c = read();
				}
			}
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

		public String next() {
			return readString();
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}
