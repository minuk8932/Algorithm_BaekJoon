import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Boj1626 {
	private static PriorityQueue<Path> pq = new PriorityQueue<>();
	private static PriorityQueue<Path> nontouch = new PriorityQueue<>();

	private static ArrayList<Node>[] tree;
	private static int[][] parent;
	private static int[][] cost;
	private static int[] deep;
	private static boolean[] visit;
	private static int[] set;

	private static int V;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		init();

		while(E-- > 0) {
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());

			pq.offer(new Path(x, y, cost));
		}

		MST();
		dfs(0, 0);
		connecting();

		System.out.println(getResult());
	}

	private static void init() {
		tree = new ArrayList[V];
		parent = new int[V][21];
		cost = new int[V][21];
		deep = new int[V];
		set = new int[V];
		visit = new boolean[V];

		for(int i = 0; i < V; i++) {
			tree[i] = new ArrayList<>();
			set[i] = -1;
			deep[i] = -1;
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
		for(Node next: tree[current]){
			if(deep[next.node] != -1) continue;
			deep[next.node] = deep[current] + 1;

			parent[next.node][0] = current;
			cost[next.node][0] = next.cost;
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
}
