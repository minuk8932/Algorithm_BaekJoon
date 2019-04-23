package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 7044번: Bad Cowtractors
 *
 *	@see https://www.acmicpc.net/problem/7044/
 *
 */
public class Boj7044 {
	private static PriorityQueue<Barn> pq = new PriorityQueue<>();
	private static int[] parent;
	
	private static class Barn implements Comparable<Barn>{
		int vertex1;
		int vertex2;
		int cost;
		
		public Barn(int vertex1, int vertex2, int cost) {
			this.vertex1 = vertex1;
			this.vertex2 = vertex2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Barn b) {
			return this.cost > b.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		init(N);
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new Barn(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
		}
		
		System.out.println(mst());
	}
	
	private static void init(int N) {
		parent = new int[N];
		for(int i = 0; i < N; i++) {
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
	
	private static int mst() {
		int cost = 0;
		
		while(!pq.isEmpty()) {
			Barn current = pq.poll();
			
			if(!merge(current.vertex1, current.vertex2)) cost += current.cost;		// 비용이 많이드는 헛간 순으로 연결
		}
		
		int count = 0;
		for(int i = 0; i < parent.length; i++) {			// 헛간이 모두 연결되지 않은 경우
			if(parent[i] < 0) count++;
		}
		
		return count > 1 ? -1: cost;
	}
}
