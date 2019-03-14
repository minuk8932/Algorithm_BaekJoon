package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16398번: 행성 연결 by kruskal
 *
 *	@see https://www.acmicpc.net/problem/16398/
 *
 */
public class Boj16398 {
	private static int[] parent;
	
	private static class Planet implements Comparable<Planet>{
		int from;
		int to;
		long cost;
		
		public Planet(int from, int to, long cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Planet p) {
			return this.cost < p.cost ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Planet> planet = new PriorityQueue<>();
		parent = new int[N * N];
		
		init();
		
		boolean[][] isVisited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				if(i == j || isVisited[i][j] || isVisited[j][i]) continue;		// 중복 제거
				isVisited[i][j] = isVisited[j][i] = true;
				
				planet.offer(new Planet(i, j, cost));
			}
		}
		
		System.out.println(mst(N, planet));
	}
	
	private static void init() {
		for(int i = 0; i < parent.length; i++) {
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
		
		if(x == y) return false;
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
		
		return true;
	}
	
	private static long mst(int n, PriorityQueue<Planet> pq) {		// Kruskal Algorithm
		long min = 0;
		
		while(!pq.isEmpty()) {				// 비용이 작은 순으로
			Planet current = pq.poll();
			
			if(merge(current.from, current.to)) {		// 그래프 묶기
				min += current.cost;
			}
		}
		
		return min;
	}
}
