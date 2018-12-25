import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class Boj15783 {
	private static int[] parent;
	
	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int M = in.readInt();
		
		init(N);

		PriorityQueue<Node> map = new PriorityQueue<>();

		for (int i = 0; i < M; i++) {
			int A = in.readInt();
			int B = in.readInt();
			
			map.offer(new Node(A, B));
		}

		search(map);
	}
	
	private static class Node implements Comparable<Node>{
		int from;
		int to;
		
		public Node(int from, int to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public int compareTo(Node n) {
			if(this.from < n.from) {
				return -1;
			}
			else if(this.from > n.from) {
				return 1;
			}
			else {
				if(this.to < n.to) return -1;
				else if(this.to > n.to) return 1;
				else return 0;
			}
		}
	}
	
	private static void init(int N) {
		parent = new int[N];
		
		for(int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}
	
	private static int find(int n) {
		if(parent[n] == n) return n;
		else return find(parent[n]);
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
	
	private static void search(PriorityQueue<Node> pq) {
		while(!pq.isEmpty()) {
			Node next = pq.poll();
			
			if(!isCycle(next.from, next.to)) {
				merge(next.from, next.to);
				
				System.out.println(next.from + " " + next.to); 
			}
		}
		
		System.out.println(getRes());
	}
	
	private static int getRes() {
		boolean[] arr = new boolean[1_000_001];
		int counts = 0;
		
		for(int i = 0; i < parent.length; i++) {
			arr[parent[i]] = true;
//			System.out.println(parent[i]);
		}
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i]) counts++;
		}
		
		return counts;
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
