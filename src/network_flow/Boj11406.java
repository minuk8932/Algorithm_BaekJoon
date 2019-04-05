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
 *	백준 11406번: 책 구매하기 2
 *
 *	@see https://www.acmicpc.net/problem/11406/
 *
 */
public class Boj11406 {
	private static ArrayList<Integer>[] connected;
	private static int[][] capacity;
	private static int[][] flow;
	private static int[] prev;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int size = N + M + 2;
		int src = size - 2, snk = size - 1;
		
		connected = new ArrayList[size];
		capacity = new int[size][size];
		flow = new int[size][size];
		
		for(int i = 0; i < connected.length; i++) {
			connected[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {					// 사람들이 구매할 책의 수
			int buy = Integer.parseInt(st.nextToken());
			capacity[src][i] = buy;
			
			connected[src].add(i);
			connected[i].add(src);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = N; i < N + M; i++) {				// 서점에 존재하는 책의 수
			int sell = Integer.parseInt(st.nextToken());
			capacity[i][snk] = sell;
			
			connected[snk].add(i);
			connected[i].add(snk);
		}
		
		for(int i = N; i < M + N; i++) {				// 서점이 사람마다 제한하는 책의 구매 수
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				int value = Integer.parseInt(st.nextToken());
				
				capacity[j][i] = value;
				connected[j].add(i);
				connected[i].add(j);
			}
		}
		
		System.out.println(networkFlow(N, M, src, snk, size));
	}
	
	private static int networkFlow(int n, int m, int source, int sink, int length) {
		int result = 0;
		prev = new int[length];
		
		while(true) {
			Arrays.fill(prev, -1);
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(source);
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next: connected[current]) {
					if(prev[next] != -1) continue;					
					if(capacity[current][next] - flow[current][next] > 0) {		// 용량이 남아있고 이전 노드 값이 저장되지 않은 경우
						prev[next] = current;
						
						q.offer(next);
					}
				}
			}
			
			if(prev[sink] == -1) break;
			
			int minFlow = Integer.MAX_VALUE;
			for(int i = sink; i != source; i = prev[i]) {							// 각 파트 최소 유량 구하기
				minFlow = Math.min(minFlow, capacity[prev[i]][i] - flow[prev[i]][i]);
			}
			
			for(int i = sink; i != source; i = prev[i]) {
				flow[prev[i]][i] += minFlow;
				flow[i][prev[i]] -= minFlow;
			}
			
			result += minFlow;
		}
		
		return result;
	}
}
