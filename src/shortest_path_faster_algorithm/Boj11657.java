package shortest_path_faster_algorithm;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11657번: 타임머신
 *
 *	@see https://www.acmicpc.net/problem/11657/
 *
 */
public class Boj11657 {
	private static final String NEW_LINE = "\n";
	private static final int INF = 1_000_000_001;
	
	private static int[] cost;
	private static int[] repeat;
	private static boolean[] inQ;
	
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] path = new ArrayList[N];
		
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int edge1 = Integer.parseInt(st.nextToken()) - 1;
			int edge2 = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			path[edge1].add(new Node(edge2, cost));
		}
			
		System.out.println(spfa(N, path));
	}
	
	private static StringBuilder spfa(int n, ArrayList<Node>[] list) {
		StringBuilder sb = new StringBuilder();
		
		repeat = new int[n];			// 시간을 무한히 되돌리는 경우
		inQ = new boolean[n];			// 큐에 존재하는 원소인지 체크
		cost = new int[n];
		Arrays.fill(cost, INF);
		
		Queue<Node> q = new LinkedList<>();
		
		inQ[0] = true;
		cost[0] = 0;
		repeat[0] = 1;
		q.offer(new Node(0, cost[0]));
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			inQ[current.edge] = false;
			
			for(Node next: list[current.edge]) {
				if(cost[next.edge] > cost[current.edge] + next.cost) {
					cost[next.edge] = cost[current.edge] + next.cost;
					
					if(!inQ[next.edge]) {
						repeat[next.edge]++;
						if(repeat[next.edge] >= n) return sb.append(-1);		// 무한히 시간을 돌린다면 비정상 종료
						
						q.offer(new Node(next.edge, cost[next.edge]));
						inQ[next.edge] = true;
					}
				}
			}
		}
		
		for(int i = 1; i < n; i++) {									// 0번을 출발지로 각 노드별 도착 최소 시간
			sb.append(cost[i] == INF ? -1 : cost[i]).append(NEW_LINE);
		}
		
		return sb;
	}
}
