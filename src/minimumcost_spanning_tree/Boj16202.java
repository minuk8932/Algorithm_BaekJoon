package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16202번: MST 게임
 *
 *	@see https://www.acmicpc.net/problem/16202/
 *
 */
public class Boj16202 {
	private static int[] parent;
	private static final String SPACE = " ";
	
	private static class Graph implements Comparable<Graph>{
		int edge1;
		int edge2;
		int cost;
		
		public Graph(int edge1, int edge2, int cost) {
			this.edge1 = edge1;
			this.edge2 = edge2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Graph g) {
			if(this.cost < g.cost) return -1;
			else if(this.cost > g.cost) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Graph[] g = new Graph[M];
		for(int cost = 1; cost < M + 1; cost++) {
			st = new StringTokenizer(br.readLine());
			g[cost - 1] = new Graph(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, cost);
		}
		
		Arrays.sort(g);
		
		int min = Integer.MAX_VALUE;
		int start = 0;								// 시작 지점을 설정해 최솟값 노드를 하나씩 자르기
		StringBuilder sb = new StringBuilder();
		
		while(K-- > 0) {
			init(N);
			
			if(min != 0) sb.append(Math.min(min, kruskal(N, M, g, start++))).append(SPACE);
			else sb.append(min).append(SPACE);		// MST의 결과가 0이 되면 이후론 쭉 0
		}
		
		System.out.println(sb);
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
	
	private static int kruskal(int n, int m, Graph[] g, int start) {
		int minCost = 0;
		
		for(int current = start; current < m; current++) {
			if(!merge(g[current].edge1, g[current].edge2)) {
				minCost += g[current].cost;
			}
		}
		
		int size = 0;
		for(int i = 0; i < n; i++) {
			if(parent[i] < -1) size = Math.max(size, -parent[i]);		// 모든 그래프가 한 집합에 속하지 못하면 MST가 생성 x
		}
		
		return n == size ? minCost: 0;
	}
}
