package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14950번: 정복자
 *
 *	@see https://www.acmicpc.net/problem/14950/
 *
 */
public class Boj14950 {
	private static int[] parent;
	
	private static class City implements Comparable<City>{
		int start;
		int node;
		int cost;
		
		public City(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
		
		public City(int start, int node, int cost) {
			this.start = start;
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(City c) {
			if(this.cost < c.cost) return -1;
			else if(this.cost > c.cost) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		init(N);
		
		ArrayList<City>[] state = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			state[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			int C = Integer.parseInt(st.nextToken());
			
			state[A].add(new City(B, C));
			state[B].add(new City(A, C));
		}
		
		System.out.println(kruskal(N, t, state));
	}
	
	private static void init(int n) {
		parent = new int[n];
		Arrays.fill(parent, -1);
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static boolean merge(int x, int y) {
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
	
	private static int kruskal(int n, int add, ArrayList<City>[] list) {
		int min = 0;
		
		PriorityQueue<City> pq = new PriorityQueue<>();
		for(City city: list[0]) {							// 정복된 1번 도시부터 탐색
			pq.offer(new City(0, city.node, city.cost));
		}
		
		while(!pq.isEmpty()) {
			City current = pq.poll();
			
			int x = find(current.start);
			int y = find(current.node);
				
			if(merge(x, y)) continue;
			min += current.cost - (parent[find(0)] + 2) * add;			// 현재 정복당한 도시의 갯수에 따라 최솟값 산정
			
			for(City city: list[current.node]) {						// 시작 도시에서 연속적으로 정복당한 도시를 다시 우선순위 큐에 저장
				pq.offer(new City(current.node, city.node, city.cost));
			}
		}
		
		return min;
	}
}
