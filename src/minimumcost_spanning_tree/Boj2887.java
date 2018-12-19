package minimumcost_spanning_tree;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 2887번: 행성 터널 by Kruskal
 *
 *	@see https://www.acmicpc.net/problem/2887/
 *	
 *
 *	Manhattan 거리처럼 계산되고, 총 데이터가 10만까지 가능함
 *	-> x, y, z 축에 대한 각각 정렬을 시도하고, 거기서 해당하는 최단 거리 및 그때의 행성 번호를 각각 모두 저장해줌 (N * 3 실시)
 *	
 *	이후 계산은 일반적인 Kruskal 알고리즘
 */
public class Boj2887 {
	private static int[] parent;
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		init(N);
		
		
		Stars[][] coordinates = new Stars[3][N];
		for(int i = 0; i < N; i++) {
			coordinates[0][i] = new Stars(i, in.readInt());
			coordinates[1][i] = new Stars(i, in.readInt());
			coordinates[2][i] = new Stars(i, in.readInt());
		}
		
		PriorityQueue<Node> map = constructGraph(N, coordinates);		// 축 정렬 및 데이터 저장
		System.out.println(kruskal(map));			// 결과 출력
	}
	
	private static class Stars implements Comparable<Stars>{
		int idx;
		int point;
		
		public Stars(int idx, int point) {
			this.idx = idx;
			this.point = point;
		}

		@Override
		public int compareTo(Stars s) {
			return this.point < s.point ? -1 : 1;
		}
	}
	
	private static void init(int n) {
		parent = new int[n];
		
		for(int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}
	
	private static PriorityQueue<Node> constructGraph(int n, Stars[][] arr){
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.sort(arr[0]);
		Arrays.sort(arr[1]);
		Arrays.sort(arr[2]);
		
		for(int i = 1; i < n; i++) {
			pq.offer(new Node(arr[0][i].idx, arr[0][i - 1].idx, Math.abs(arr[0][i].point - arr[0][i - 1].point)));
		}
		
		for(int i = 1; i < n; i++) {
			pq.offer(new Node(arr[1][i].idx, arr[1][i - 1].idx, Math.abs(arr[1][i].point - arr[1][i - 1].point)));
		}
		
		for(int i = 1; i < n; i++) {
			pq.offer(new Node(arr[2][i].idx, arr[2][i - 1].idx, Math.abs(arr[2][i].point - arr[2][i - 1].point)));
		}
		
		return pq;
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
	
	private static int find(int from) {
		if(parent[from] == from) return from;
		else return find(parent[from]);
	}
	
	private static boolean isCycle(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return true;
		else return false;
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x < y) parent[y] = x;
		else parent[x] = y;
	}
	
	private static int kruskal(PriorityQueue<Node> pq) {		// 크루스칼 알고리즘
		int minCost = 0;
		
		while(!pq.isEmpty()) {
			Node next = pq.poll();
			
			if(!isCycle(next.node1, next.node2)) {		// 이미 연결했는가?
				merge(next.node1, next.node2);			// 연결안된 최솟값이면 연결 후
				minCost += next.cost;					// 최솟값을 총 비용에 더함
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
