package bellman_ford;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1219번: 오민식의 고민
 *
 *	@see https://www.acmicpc.net/problem/1219/
 *
 */
public class Boj1219 {
	private static class Node{
		int vertex;
		long cost;
		
		public Node(int vertex, long cost) {
			this.vertex = vertex;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] path = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			path[from].add(new Node(to, cost));
		}
		
		int[] earn = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			earn[i] = Integer.parseInt(st.nextToken());
		}
		
		long result = bellmanFord(N, s, e, path, earn);
		
		if(result == Long.MIN_VALUE) System.out.println("Gee");
		else if(result == Long.MAX_VALUE) System.out.println("gg");
		else System.out.println(-result);
	}
	
	private static long bellmanFord(int n, int start, int end, ArrayList<Node>[] list, int[] plus) {
		long[] cost = new long[n];
		Arrays.fill(cost, Long.MAX_VALUE);
		
		cost[start] = -plus[start];
		
		for(int idx = 0; idx < 100; idx++) {						// 무식하게 반복 (최대 입력 만큼, 플로이드 와샬도 가능할 듯?)
			for(int vertex = 0; vertex < n; vertex++) {
				for(Node next: list[vertex]) {
					if(cost[vertex] == Long.MAX_VALUE) continue;	// 값 갱신이 안되어있는 경우 
					
					if(cost[vertex] == Long.MIN_VALUE) {			// 계속 손해를 보는 경우
						cost[next.vertex] = Long.MIN_VALUE;
						continue;
					}
					
					if(cost[next.vertex] > cost[vertex] + next.cost - plus[next.vertex]) {		// 값 갱신 경로
						cost[next.vertex] = cost[vertex] + next.cost - plus[next.vertex];
						
						if(idx >= n - 1) cost[next.vertex] = Long.MIN_VALUE;			// 최솟값이 계속 갱신된 경우
					}
				}
			}
		}
		
		return cost[end];
	}
}
