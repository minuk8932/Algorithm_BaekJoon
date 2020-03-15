package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 15481번: 그래프와 MST
 *
 * @see https://www.acmicpc.net/problem/15481/
 *
 */
public class Boj15481 {
	private static final String NEW_LINE = "\n";
	private static final long CIPHER = 1_000_000L;

	private static ArrayList<Path> query = new ArrayList<>();
	private static HashSet<Long> component = new HashSet<>();
	private static int[] sets;

	private static ArrayList<Path>[] tree;
	private static int[][] parent;
	private static int[][] values;
	private static int[] deep;
	private static boolean[] visit;

	private static int N;

	private static class Path implements Comparable<Path>{
		int node1;
		int node2;
		int vertex;
		int cost;

		public Path(int vertex, int cost){
			this.vertex = vertex;
			this.cost = cost;
		}
		
		public Path(int node1, int node2, int cost) {
			this.node1 = node1;
			this.node2 = node2;
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
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		init();
		
		PriorityQueue<Path> paths = new PriorityQueue<>();
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken()) - 1;
			int n2 = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			
			paths.add(new Path(n1, n2, c));
			query.add(new Path(n1, n2, c));
		}

		long mst = kruskal(paths);
		dfs(0, 0);
		connecting();

		StringBuilder sb = new StringBuilder();
		for(Path q : query){
			long visit = q.node1 * CIPHER + q.node2;

			if(component.contains(visit)){
				sb.append(mst).append(NEW_LINE);
			}
			else{
				int lca = LCA(q.node1, q.node2);
				sb.append(mst + q.cost - getMax(q.node1, q.node2, lca)).append(NEW_LINE);		// find specific mst
			}
		}

		System.out.println(sb.toString());
	}
	
	private static void init() {
		tree = new ArrayList[N];
		sets = new int[N];
		parent = new int[N][21];
		values = new int[N][21];
		deep = new int[N];
		visit = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			sets[i] = -1;
			tree[i] = new ArrayList<>();
		}
	}
	
	private static int find(int x) {
		if(sets[x] < 0) return x;
		else return sets[x] = find(sets[x]);
	}
	
	private static boolean merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return true;
		
		if(sets[x] < sets[y]) {
			sets[x] += sets[y];
			sets[y] = x;
		}
		else {
			sets[y] += sets[x];
			sets[x] = y;
		}
		
		return false;
	}
	
	private static long kruskal(PriorityQueue<Path> pq) {
		long cost = 0;

		while(!pq.isEmpty()) {
			Path current = pq.poll();
			
			if(merge(current.node1, current.node2)) continue;
			tree[current.node1].add(new Path(current.node2, current.cost));			// make tree with mst
			tree[current.node2].add(new Path(current.node1, current.cost));

			component.add(CIPHER * current.node1 + current.node2);					// mst component
			cost += current.cost;
		}

		return cost;
	}

	private static void dfs(int current, int depth){
		visit[current] = true;
		deep[current] = depth;

		for(Path next: tree[current]){
			if(visit[next.vertex]) continue;

			parent[next.vertex][0] = current;
			values[next.vertex][0] = next.cost;

			dfs(next.vertex, depth + 1);
		}
	}

	private static void connecting(){
		for(int p = 1; p < 21; p++){
			for(int cur = 0; cur < N; cur++){
				parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
				values[cur][p] = Math.max(values[parent[cur][p - 1]][p - 1], values[cur][p - 1]);	// make max in path
			}
		}
	}

	private static int LCA(int x, int y){
		if(deep[x] > deep[y]){
			int swap = x;
			x = y;
			y = swap;
		}

		for(int i = 20; i >= 0; i--){
			int jump = 1 << i;
			if(deep[y] - deep[x] >= jump) y = parent[y][i];
		}

		if(y == x) return x;

		for(int i = 20; i >= 0; i--){
			if(parent[x][i] == parent[y][i]) continue;

			x = parent[x][i];
			y = parent[y][i];
		}

		return parent[x][0];
	}

	private static int getMax(int x, int y, int lca){
		int max = 0;

		int xDeep = deep[x] - deep[lca];
		int yDeep = deep[y] - deep[lca];

		for (int i = 20; i >= 0; i--) {
			int jump = 1 << i;

			if (xDeep >= jump) {
				max = Math.max(max, values[x][i]);			// find max in path
				xDeep -= jump;
				x = parent[x][i];
			}
		}

		for (int i = 20; i >= 0; i--) {
			int jump = 1 << i;

			if (yDeep >= jump) {
				max = Math.max(max, values[y][i]);
				yDeep -= jump;
				y = parent[y][i];
			}
		}

		return max;
	}
}
