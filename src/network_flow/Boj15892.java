package network_flow;
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
 *	백준 15892반: 사탕 줍는 로봇
 *
 *	@see https://www.acmicpc.net/problem/15892/
 *
 */
public class Boj15892 {
	private static ArrayList<Integer>[] path;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] cap = new int[n + 1][n + 1];

		path = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) path[i] = new ArrayList<>();

		while(m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			cap[from][to] += cost;
			cap[to][from] += cost;
			
			path[from].add(to);
			path[to].add(from);
		}
		
		System.out.println(networkFlow(n, cap));
	}
	
	private static int networkFlow(int N, int[][] capacity) {
		int[][] flow = new int[N + 1][N + 1];
		int robots = 0, source = 1, sink = N;
		
		while(true) {
			int[] visit = new int[N + 1];			
			visit = bfs(N, capacity, flow, visit, source, sink);		// 로봇을 배치할 수 있는 구간 설정
			
			if(visit[sink] == -1) break;								// 종점에 더 이상 보낼 로봇이 없는 경우
			
			int minFlow = Integer.MAX_VALUE;
			for(int edge = sink; edge != source; edge = visit[edge]) {			// 역으로 연결된 복도를 통해 가능한 최소 로봇 수 계산
				minFlow = Math.min(minFlow, capacity[visit[edge]][edge] - flow[visit[edge]][edge]);
			}
			
			for(int edge = sink; edge != source; edge = visit[edge]) {			// 계산된 최소 로봇 수 배치
				flow[visit[edge]][edge] += minFlow;
				flow[edge][visit[edge]] -= minFlow;
			}
			
			robots += minFlow;						// 수행 횟수마다 배치 가능한 로봇의 최소 수
		}
		
		return robots;
	}
	
	private static int[] bfs(int N, int[][] capacity, int[][] flow, int[] isVisited, int src, int snk) {
		Arrays.fill(isVisited, -1);
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(src);
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: path[current]) {
				if(isVisited[next] != -1) continue;
				
				if(capacity[current][next] > flow[current][next]) {
					isVisited[next] = current;
					if(next == snk) return isVisited;
					
					q.offer(next);
				}
			}
		}
		
		return isVisited;
	}
}
