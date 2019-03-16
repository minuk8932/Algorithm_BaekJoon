package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13418번: 학교 탐방하기
 *
 *	@see https://www.acmicpc.net/problem/13418/
 *
 */
public class Boj13418 {
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
			return this.cost == 1 ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken()) + 1;
		
		PriorityQueue<Path> minPq = new PriorityQueue<>();
		PriorityQueue<Path> maxPq = new PriorityQueue<>();
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int type = Integer.parseInt(st.nextToken());
			
			minPq.offer(new Path(from, to, type));					// 내리막 기준 정렬
			maxPq.offer(new Path(from, to, type == 0 ? 1 : 0));		// 오르막 기준 정렬
		}
		
		init(N);
		long max = kruskal(N, maxPq, 1);
		
		init(N);
		long min = kruskal(N, minPq, 0);
		
		System.out.println(max - min);
	}
	
	private static void init(int n) {
		parent = new int[n + 1];
		
		for(int i = 0; i < n + 1; i++) {
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
	
	private static long kruskal(int n, PriorityQueue<Path> pq, int type) {
		long cost = 0;
		
		while(!pq.isEmpty()) {
			Path current = pq.poll();
			
			if(merge(current.from, current.to)) {
				if(current.cost == type) cost++;		// 경우에 따라 오르막길이 나온 경우 +1
			}
		}
		
		return cost * cost;
	}
}
