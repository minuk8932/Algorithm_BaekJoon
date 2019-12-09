package disjoint_set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17398번: 통신망 분할
 *
 *	@see https://www.acmicpc.net/problem/17398/
 *
 */
public class Boj17398 {
	private static int[] parent;
	private static boolean[] used;
	
	private static class Pair{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		init(N);
		
		Pair[] tie = new Pair[M];
		used = new boolean[M];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			tie[i] = new Pair(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		}
		
		int[] query = new int[Q];
		for(int i = 0; i < Q; i++) {
			query[i] = Integer.parseInt(br.readLine()) - 1;
			used[query[i]] = true;
		}
		
		System.out.println(divide(N, M, Q, tie, query));
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
	
	private static long merged(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return 0;
		
		long size = (long) parent[x] * (long) parent[y];
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
		
		return size;
	}
	
	private static long divide(int n, int m, int q, Pair[] p, int[] arr) {
		long cost = 0;
		
		for(int i = 0 ; i < m; i++) {
			if(used[i]) continue;
			merged(p[i].x, p[i].y);						// merge not input first
		}
		
		for(int i = arr.length - 1; i >= 0; i--) {
			cost += merged(p[arr[i]].x, p[arr[i]].y);	// reversed sets & cal set size
		}
		
		return cost;
	}
}
