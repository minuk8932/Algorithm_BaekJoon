package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 1719번: 택배
 *
 *	@see https://www.acmicpc.net/problem/1719/
 *
 */
public class Boj1719 {
	private static ArrayList<Path>[] list;
	private static final int INF = 1_000_000_000;
	
	private static int[] cost;
	
	private static final String NULL = "-";
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static class Path implements Comparable<Path>{
		int node;
		int cost;
		
		public Path(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Path p) {
			return this.cost < p.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			list[i] = new ArrayList<>();
		}
		
		while(m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int vertex1 = Integer.parseInt(st.nextToken()) - 1;
			int vertex2 = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			list[vertex1].add(new Path(vertex2, cost));
			list[vertex2].add(new Path(vertex1, cost));
		}
		
		getResult(n);
	}
	
	private static void getResult(int n) {
		int[][] path = new int[n][n];
		cost = new int[n];
		
		for(int i = 0; i < n; i++) {
			Arrays.fill(path[i], INF);
		}
		
		for(int from = 0; from < n; from++) {
			Arrays.fill(cost, INF);
			
			for(int to = 0; to < n; to++) {
				if(from == to) continue;
				path[from][to] = dijkstra(n, from, to);			// from ~ to 최단경로 구한 후 최초 방문 노드 저장
			}
		}

		System.out.println(getPathInfo(n, path));
	}
	
	private static String getPathInfo(int n, int[][] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {				// 경로 출력
			for(int j = 0; j < n; j++) {
				sb.append(arr[i][j] == INF ? NULL: arr[i][j]).append(SPACE);
			}
			
			sb.append(NEW_LINE);
		}
		
		return sb.toString();
	}
	
	private static int dijkstra(int n, int start, int end) {
		PriorityQueue<Path> pq = new PriorityQueue<>();
		pq.offer(new Path(start, 0));
		
		cost[start] = 0;
		
		while(!pq.isEmpty()) {
			Path current = pq.poll();
			
			for(Path next: list[current.node]) {
				if(cost[next.node] > cost[current.node] + next.cost) {
					cost[next.node] = cost[current.node] + next.cost;
					
					pq.offer(new Path(next.node, cost[next.node]));
				}
			}
		}

		return reversedBfs(cost, end);			// 너비 우선 탐색으로 역방향 탐색 후 최초 방문 노드 반환
	}
	
	private static int reversedBfs(int[] cost, int idx) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(idx);
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(Path before: list[current]) {
				if(cost[current] - before.cost == cost[before.node]) {
					idx = current;
					
					q.offer(before.node);
				}
			}
		}
		
		return idx + 1;
	}
}
