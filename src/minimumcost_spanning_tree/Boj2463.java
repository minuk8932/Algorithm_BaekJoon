package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2463번: 비용
 *
 *	@see https://www.acmicpc.net/problem/2463/
 *
 */
public class Boj2463 {
	private static final int MOD = 1_000_000_000;
	private static int[] parent;
	private static long[] size;
	
	private static class Node implements Comparable<Node>{
		int vertex1;
		int vertex2;
		int cost;
		
		public Node(int vertex1, int vertex2, int cost) {
			this.vertex1 = vertex1;
			this.vertex2 = vertex2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost > n.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Node> path = new PriorityQueue<>();
		long total = 0;
		init(N);
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken()) - 1;
			int v2 = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			path.offer(new Node(v1, v2, cost));
			
			total += cost;
		}

		System.out.println(mstByKruskal(path, total));
	}
	
	private static void init(int n) {
		parent = new int[n];
		size = new long[n];
		
		for(int i = 0; i < n; i++) {
			parent[i] = i;
			size[i] = 1;				// 집합 크기 따로 저장, 집합 크기가 매우 커질 수 있음
		}
	}
	
	private static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	private static void merge(int x, int y) {
		if(x > y) {
			parent[y] = x;
			size[x] += size[y];
			size[y] = 1;
		}
		else {
			parent[x] = y;
			size[y] += size[x];
			size[x] = 1;
		}
	}
	
	private static long mstByKruskal(PriorityQueue<Node> pq, long cost) {
		long result = 0;
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			int[] vParent = {find(current.vertex1), find(current.vertex2)};
			
			if(vParent[0] != vParent[1]) {						// 부모가 다른 경우 집합처리
				result += (size[vParent[0]] * size[vParent[1]] * cost) % MOD;	// 집합1 * 집합2 * 총비용
				merge(vParent[0], vParent[1]);
			}
			
			cost -= current.cost;			// 현재 비용을 통한 노드가 존재하고 있으니 해당 비용 제거
		}
		
		return result % MOD;
	}
}
