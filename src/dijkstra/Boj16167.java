package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16167번: A Great Way
 *
 *	@see https://www.acmicpc.net/problem/16167/
 *
 */
public class Boj16167 {
	private static final String NO_ANSWER = "It is not a great way.";
	private static final String SPACE = " ";
	
	private static class Node implements Comparable<Node>{
		int edge;
		int cost;
		
		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1: 1;
		}
	}
	
	private static class Result{
		int cost;
		int count;
		
		public Result(int cost, int count) {
			this.cost = cost;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] graph = new ArrayList[N + 1];
		for(int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		while(R-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int basic = Integer.parseInt(st.nextToken());
			int minutes = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			int cost = time <= 10 ? basic : basic + (time - 10) * minutes;		// 경로의 비용을 미리 구함
			graph[from].add(new Node(to, cost));
		}
		
		System.out.println(dijkstra(N, graph));
	}
	
	private static StringBuilder dijkstra(int n, ArrayList<Node>[] list) {
		StringBuilder sb = new StringBuilder();
		
		int[] isVisited = new int[n + 1];
		int[] counts = new int[n + 1];
		Arrays.fill(isVisited, Integer.MAX_VALUE);
		
		ArrayList<Result> arr = new ArrayList<>();
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));
			
		isVisited[1] = 0;
		counts[1] = 1;
			
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			for(Node next: list[current.edge]) {
				if(isVisited[next.edge] >= isVisited[current.edge] + next.cost) {
					isVisited[next.edge] = isVisited[current.edge] + next.cost;	// 비용
					counts[next.edge] = counts[current.edge] + 1;				// 노드를 지나는 횟수
					
					if(next.edge == n) arr.add(new Result(isVisited[next.edge], counts[next.edge]));
					pq.offer(new Node(next.edge, isVisited[next.edge]));
				}
			}
		}
		
		int minCost = Integer.MAX_VALUE, routers = Integer.MAX_VALUE;
		
		for(Result values: arr) {
			if(minCost > values.cost) minCost = values.cost;		// 비용의 최소
		}
		
		for(Result values: arr) {
			if(minCost == values.cost) {
				if(routers > values.count) routers = values.count;	// 비용의 최소를 기반으로 거치는 노드의 최소 갯수 구하기
			}
		}

		return routers == Integer.MAX_VALUE ? sb.append(NO_ANSWER) : sb.append(minCost).append(SPACE).append(routers);
	}
}
