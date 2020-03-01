package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 13424번: 비밀 모임
 *
 * @see https://www.acmicpc.net/problem/13424/
 *
 */
public class Boj13424 {
	private static final String NEW_LINE = "\n";

	private static ArrayList<Node>[] path;
	private static int[] cost;
	private static final int INF = 1_000_000_000;
	
	private static class Node{
		int edge;
		int cost;
		
		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			path = new ArrayList[N];
			cost = new int[N];
			for(int i = 0; i < N; i++) {
				path[i] = new ArrayList<>();
			}
			
			while(M-- > 0) {
				st = new StringTokenizer(br.readLine());
				int e1 = Integer.parseInt(st.nextToken()) - 1;
				int e2 = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				
				path[e1].add(new Node(e2, c));
				path[e2].add(new Node(e1, c));
			}
			
			int K = Integer.parseInt(br.readLine());
			ArrayList<Integer> friend = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			while(K-- > 0) {
				friend.add(Integer.parseInt(st.nextToken()) - 1);
			}

			int result = 0, min = INF;
			for(int i = N - 1; i >= 0; i--){
				int target = bfs(N, friend, i);

				if(target <= min){
					min = target;
					result = i;
				}
			}
			
			sb.append(result + 1).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int bfs(int n, ArrayList<Integer> end, int start) {
		Arrays.fill(cost, INF);

		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(start, 0));
		cost[start] = 0;

		while(!q.isEmpty()){
			Node current = q.poll();

			for(Node next: path[current.edge]){
				if(cost[next.edge] > cost[current.edge] + next.cost){
					cost[next.edge] = cost[current.edge] + next.cost;

					q.offer(new Node(next.edge, cost[next.edge]));
				}
			}
		}

		int sum = 0;
		for(int e: end){			// add all values from start to end
			sum += cost[e];
		}
		
		return sum;
	}
}
