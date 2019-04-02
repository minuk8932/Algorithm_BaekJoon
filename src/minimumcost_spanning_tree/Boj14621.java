package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14621번: 나만 안되는 연애
 *
 *	@see https://www.acmicpc.net/problem/14621/
 *
 */
public class Boj14621 {
	private static int[] parent;
	
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
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		init(N);
		
		boolean[] gender = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			gender[i] = st.nextToken().charAt(0) == 'M' ? true: false;
		}
		
		PriorityQueue<Path> link = new PriorityQueue<>();
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			link.add(new Path(from, to, cost));
		}
		
		System.out.println(kruskal(N, link, gender));
	}
	
	private static void init(int n) {
		parent = new int[n];
		
		for(int i = 0; i < n; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		return parent[x] = find(parent[x]);
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
	
	private static int kruskal(int n, PriorityQueue<Path> pq, boolean[] arr) {
		int min = 0;
		
		while(!pq.isEmpty()) {
			Path current = pq.poll();
			
			if((arr[current.from] ^ arr[current.to]) && !merge(current.from, current.to)) {
				min += current.cost;					// 두 학교가 여초와 남초이면서, 아직 같은 집합이 아닌경우 경로를 받아 최단거리 생성
			}
		}
		
		for(int i = 0; i < parent.length; i++) {		// 묶이지 않은 학교가 있는 경우
			if(parent[i] == -1) return -1;
		}
		
		return min;
	}
}
