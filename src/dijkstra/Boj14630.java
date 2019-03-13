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
 *	백준 14630번: 변신 로봇
 *
 *	@see https://www.acmicpc.net/problem/14630/
 *
 */
public class Boj14630 {
	private static final int INF = 1_000_000_001;
	
	private static class Node implements Comparable<Node>{
		int edge;
		int cost;
		
		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Node>[] robot = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			robot[i] = new ArrayList<>();
		}
		
		String[] product = new String[N];
		for(int i = 0; i < N; i++) {
			product[i] = br.readLine();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {					// 모든 간선 연결
				int cost = distance(product[i], product[j]);
				
				robot[i].add(new Node(j, cost));
				robot[j].add(new Node(i, cost));
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		System.out.println(dijkstra(N, robot, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
	}
	
	private static int distance(String str1, String str2) {
		int d = 0;
		int leng = str1.length();		// 문제 조건에 따라 길이가 다른것은 고려하지 않음
		
		for(int i = 0; i < leng; i++) {										// 부품간 비용 계산
			int cost = (str1.charAt(i) - '0') - (str2.charAt(i) - '0');
			d += (cost * cost);
		}

		return d;
	}

	private static int dijkstra(int n, ArrayList<Node>[] graph, int start, int end) {
		int[] cost = new int[n];
		Arrays.fill(cost, INF);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		cost[start] = 0;
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			for(Node next: graph[current.edge]) {						// 최소 비용을 찾자
				if(cost[next.edge] > cost[current.edge] + next.cost) {
					cost[next.edge] = cost[current.edge] + next.cost;
					
					pq.offer(new Node(next.edge, cost[next.edge]));
				}
			}
		}
		
		return cost[end];
	}
}
