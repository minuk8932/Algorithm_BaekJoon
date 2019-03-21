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
 *	백준 6086번: 최대 유량
 *
 *	@see https://www.acmicpc.net/problem/6086/
 *
 */
public class Boj6086 {
	private static final int SIZE = 52;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] capacity = new int[SIZE][SIZE];
		int[][] flow = new int[SIZE][SIZE];
		
		ArrayList<Integer>[] path = new ArrayList[SIZE];
		for(int i = 0; i < SIZE; i++) {
			path[i] = new ArrayList<>();
		}
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char prev = st.nextToken().charAt(0);
			char post = st.nextToken().charAt(0);
			int cost = Integer.parseInt(st.nextToken());
			
			int from = charToInt(prev);
			int to = charToInt(post);
			
			capacity[from][to] += cost;
			capacity[to][from] += cost;
			
			path[from].add(to);
			path[to].add(from);
		}
		
		System.out.println(networkFlow(path, capacity, flow));
	}
	
	private static int networkFlow(ArrayList<Integer>[] path, int[][] capacity, int[][] flow) {
		int result = 0;
		int source = 0, sink = 'Z' - 'A';
		
		while(true) {
			int[] visit = new int[SIZE];
			Arrays.fill(visit, -1);
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(source);
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next: path[current]) {
					if(visit[next] != -1) continue;						// 이미 흘러보낸 간선
					
					if(capacity[current][next] > flow[current][next]) {	// c - f > 0: 흐를 수 있는 유량이 남아있는 경우
						visit[next] = current;
						if(next == sink) break;
						
						q.offer(next);
					}
				}
			}
			
			if(visit[sink] == -1) break;		// 간선이 더 이상 없는 경우
			
			int minFlow = Integer.MAX_VALUE;
			for(int i = sink; i != source; i = visit[i]) {			// 유량 중 최소 유량을 구함
				minFlow = Math.min(minFlow, capacity[visit[i]][i] - flow[visit[i]][i]);
			}
			
			for(int i = sink; i != source; i = visit[i]) {			// 각 경로 및 역경로로 흐를 수 있는 유량 추가
				flow[visit[i]][i] += minFlow;
				flow[i][visit[i]] -= minFlow;
			}
			
			result += minFlow;					// 회차 당 흐른 유량
		}
		
		return result;
	}
	
	private static int charToInt(char a) {		
		return a >= 'A' && a <= 'Z' ? a - 'A': (a - 'a') + 26;
	}
}

