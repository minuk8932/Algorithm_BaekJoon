import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj15481 {
	private static final String NEW_LINE = "\n";
	
	private static int[] parent;
	private static HashMap<Integer, Path> check = new HashMap<>();
	
	private static class Path implements Comparable<Path>{
		int idx;
		int node1;
		int node2;
		int cost;
		
		public Path(int idx, int node1, int node2, int cost) {
			this.idx = idx;
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
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		init(N);
		
		PriorityQueue<Path> paths = new PriorityQueue<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken()) - 1;
			int n2 = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			
			paths.add(new Path(i, n1, n2, c));
		}
		
		System.out.println(kruskal(N, M, paths));
	}
	
	private static void init(int n) {
		parent = new int[n];
		
		for(int i = 0; i < n; i++) {
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
	
	private static String kruskal(int n, int m, PriorityQueue<Path> pq) {
		boolean[] merged = new boolean[m];
		
		long[] result = new long[m];
		long cost = 0;
		
		while(!pq.isEmpty()) {							// 경로상 최댓값은 sparse table로 구해야 하는데...
			Path current = pq.poll();
			
			if(merge(current.node1, current.node2)) {
				check.put(current.idx, current);
				continue;
			}
			
			cost += current.cost;
			merged[current.idx] = true;
		}
		
		for(int i = 0; i < m; i++) {
			if(merged[i]) {
				result[i] = cost;
			}
			else {
				Path target = check.get(i);
				result[i] = cost + target.cost - 0;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			sb.append(result[i]).append(NEW_LINE);
		}
		
		return sb.toString();
	}
}
