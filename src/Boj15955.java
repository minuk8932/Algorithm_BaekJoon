import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj15955 {
	private static final String Y = "YES";
	private static final String N = "NO";
	private static final String NEW_LINE = "\n";
	
	private static int[] parent;
	private static int[] result;
	
	private static class Point{
		int idx;
		int x;
		int y;
		
		public Point(int idx, int x, int y) {
			this.idx = idx;
			this.x = x;
			this.y = y;
		}
	}
	
	private static class Element implements Comparable<Element>{
		int idx1;
		int idx2;
		int diff;
		
		public Element(int idx1, int idx2, int diff) {
			this.idx1 = idx1;
			this.idx2 = idx2;
			this.diff = diff;
		}

		@Override
		public int compareTo(Element e) {
			return this.diff < e.diff ? -1: 1;
		}
	}
	
	private static class Sort implements Comparator<Point>{
		boolean coor;
		
		public Sort(boolean coor) {
			this.coor = coor;
		}
		
		@Override
		public int compare(Point p1, Point p2) {
			if(coor) {
				if(p1.x < p2.x) return -1;
				else if(p1.x > p2.x) return 1;
				else return 0;
			}
			else {
				if(p1.y < p2.y) return -1;
				else if(p1.y > p2.y) return 1;
				else return 0;
			}
		}
	}
	
	private static class QueryPairs implements Comparable<QueryPairs> {
		int idx;
		int from;
		int to;
		int hp;
		
		public QueryPairs(int idx, int from, int to, int hp) {
			this.idx = idx;
			this.from = from;
			this.to = to;
			this.hp = hp;
		}

		@Override
		public int compareTo(QueryPairs qp) {
			return this.hp < qp.hp ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		Point[] checkX = new Point[N + 1];
		Point[] checkY = new Point[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			checkX[i] = new Point(i, x, y);
			checkY[i] = new Point(i, x, y);
		}
		
		Arrays.sort(checkX, new Sort(true));
		Arrays.sort(checkY, new Sort(false));
		
		PriorityQueue<Element> coordinate = new PriorityQueue<>();
		coordinate = pqInitiate(checkX, checkY);
		
		parent = new int[N + 1];
		init(N);
		
		QueryPairs[] pair = new QueryPairs[Q];
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			
			pair[i] = new QueryPairs(i, A, B, X);
		}
		
		Arrays.sort(pair);
		
		for(int i = 0; i < Q; i++) {
			QueryPairs current = pair[i];
			
		}
		
		System.out.println();
	}
	
	private static PriorityQueue<Element> pqInitiate(Point[] p1, Point[] p2) {
		PriorityQueue<Element> pq = new PriorityQueue<>();
		
		for(int i = 1; i < p1.length; i++) {
			pq.offer(new Element(i, i - 1, Math.abs(p1[i].x - p1[i - 1].x)));
		}
		
		for(int i = 1; i < p2.length; i++) {
			pq.offer(new Element(i, i - 1, Math.abs(p2[i].x - p2[i - 1].x)));
		}
		
		return pq;
	}
	
	private static void init(int n) {
		for(int i = 1; i < n + 1; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}
}
