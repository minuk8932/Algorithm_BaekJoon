package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17833번: 홍익대학교 지하캠퍼스
 *
 *	@see https://www.amicpc.net/problem/17833/
 *
 */
public class Boj17833 {
	private static ArrayList<Node>[] path;
	private static long[] dist;
	
	private static final long INF = 1_000_000_000_000L;
	
	private static class Model{
		int height;
		int time;
		int exit1;
		int exit2;
		
		public Model(int height, int time, int exit1, int exit2) {
			this.height = height;
			this.time = time;
			this.exit1 = exit1;
			this.exit2 = exit2;
		}
	}
	
	private static class Node implements Comparable<Node>{
		int node;
		long cost;
		
		public Node(int node, long cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			if(this.cost < n.cost) return -1;
			else if(this.cost > n.cost) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int M = Integer.parseInt(br.readLine());
		ArrayList<Model> campus = new ArrayList<>();
		
		for(int i = 0; i < M ;i++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			int E1 = Integer.parseInt(st.nextToken());
			int E2 = Integer.parseInt(st.nextToken());
			
			campus.add(new Model(H, T, E1, E2));
		}
		
		makePath(N, campus);
		System.out.println(construction(N, R, D, campus));
	}
	
	private static void makePath(int n, ArrayList<Model> models) {
		path = new ArrayList[n + 1];
		dist = new long[n + 1];
		
		for(int i = 0; i < n + 1; i++) {
			path[i] = new ArrayList<>();
			dist[i] = INF;
		}
		
		for(Model m: models) {
			for(int idx = 0; idx <= n - m.height; idx++) {					// graph remodeling
				path[m.exit1 + idx].add(new Node(m.exit2 + idx, m.time));
				path[m.exit2 + idx].add(new Node(m.exit1 + idx, m.time));
			}
		}
	}
	
	private static long construction(int n, int r, int d, ArrayList<Model> models) {
		Arrays.fill(dist, INF);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(r, 0));
		
		dist[r] = 0;
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			if(dist[current.node] < current.cost) continue;
			
			for(Node next: path[current.node]) {
				if(dist[next.node] > dist[current.node] + next.cost) {			// get the Shortest
					dist[next.node] = dist[current.node] + next.cost;
					
					pq.offer(new Node(next.node, dist[next.node]));
				}
			}
		}
		
		return dist[d] == INF ? -1: dist[d];
	}
}
