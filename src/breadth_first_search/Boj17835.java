package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17835번: 면접보는 승범이네
 *
 *	@see https://www.acmicpc.net/problem/17835/
 *
 */
public class Boj17835 {
	private static ArrayList<Integer> start = new ArrayList<>();
	private static final long INF = Long.MAX_VALUE;
	private static final String NEW_LINE = "\n";
	
	private static class Node{
		int node;
		long cost;
		
		public Node(int node, long cost) {
			this.node = node;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] path = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			path[to].add(new Node(from, cost));						// input reverse direction
		}
		
		st = new StringTokenizer(br.readLine());
		while(K-- > 0) {
			start.add(Integer.parseInt(st.nextToken()) - 1);		// find interview place
		}
		
		System.out.println(bfs(N, path));
	}
	
	private static String bfs(int n, ArrayList<Node>[] list) {
		Queue<Node> q = new LinkedList<>();
		long[] visit = new long[n];
		Arrays.fill(visit, INF);
		
		for(int s: start) {											// search from interview place
			q.offer(new Node(s, 0));
			visit[s] = 0;
		}
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			
			for(Node next: list[current.node]) {
				if(visit[next.node] > visit[current.node] + next.cost) {
					visit[next.node] = visit[current.node] + next.cost;
					
					q.offer(new Node(next.node, visit[next.node]));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		long max = 0;
		
		for(int i = 0; i < n; i++) {					// find max distance
			if(visit[i] == INF) continue;
			max = Math.max(visit[i], max);
		}
		
		for(int i = 0; i < n; i++) {					// min region number from max distance 
			if(visit[i] == max) {
				sb.append(i + 1).append(NEW_LINE).append(max);
				break;
			}
		}
		
		return sb.toString();
	}
}
