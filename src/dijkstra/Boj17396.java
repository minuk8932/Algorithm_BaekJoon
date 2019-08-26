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
 *	백준 17396번: 백도어
 *
 *	@see https://www.acmicpc.net/problem/17396/
 *
 */
public class Boj17396 {
	private static ArrayList<Node>[] path;
	
	private static class Node implements Comparable<Node>{
		int node;
		long cost;
		
		public Node(int node, long cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		path = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		
		boolean[] sight = new boolean[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			sight[i] = st.nextToken().equals("0") ? false: true;
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			if(from != N - 1 && to != N - 1) {
				if(sight[from] || sight[to]) continue;		// 넥서스가 아닌데 시야가 보이는 경우
			}

			path[from].add(new Node(to, cost));
			path[to].add(new Node(from, cost));
		}
		
		System.out.println(dijkstra(N));
	}
	
	private static long dijkstra(int n) {
		long min = Long.MAX_VALUE;
		
		long[] cost = new long[n];
		Arrays.fill(cost, min);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(n - 1, 0));						// 넥서스부터
		
		cost[n - 1] = 0;
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			for(Node next: path[current.node]) {
				if(cost[next.node] > cost[current.node] + next.cost) {
					cost[next.node] = cost[current.node] + next.cost;

					if(next.node == 0) min = Math.min(min, cost[next.node]);		// 시작지점까지 도달 가능한가?					
					pq.offer(new Node(next.node, cost[next.node]));
				}
			}
		}
		
		return min == Long.MAX_VALUE ? -1: min;
	}
}
