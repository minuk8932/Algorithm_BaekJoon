package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 4650번: Jungle Roads
 *
 *	@see https://www.acmicpc.net/problem/4650/
 *
 */
public class Boj4650 {
	private static int[] parent;
	private static final String NEW_LINE = "\n";
	
	private static class Map implements Comparable<Map>{
		int from;
		int to;
		int dist;
		
		public Map(int from, int to, int dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Map m) {
			return this.dist < m.dist ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			init(N);
			
			PriorityQueue<Map> path = new PriorityQueue<>();
			int loop = N - 1;
			
			while(loop-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int from = st.nextToken().charAt(0) - 'A';					// 시작 지점에서
				int count = Integer.parseInt(st.nextToken());
				
				while(count-- > 0) {
					path.add(new Map(from, st.nextToken().charAt(0) - 'A', Integer.parseInt(st.nextToken())));	// 경로와 거리 저장
				}
			}
			
			sb.append(kruskal(N, path)).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
	
	private static void init(int n) {
		parent = new int[n];
		for(int i = 0; i < n; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {					// 서로소 집합 알고리즘
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static void merge(int x, int y) {		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}
	
	private static int kruskal(int n, PriorityQueue<Map> pq) {		 // 크루스칼 알고리즘
		int cost = 0;
		
		while(!pq.isEmpty()) {
			Map current = pq.poll();
			
			int x = find(current.from);
			int y = find(current.to);
			
			if(x == y) continue;
			
			merge(x, y);
			cost += current.dist;
		}
		
		return cost;
	}
}
