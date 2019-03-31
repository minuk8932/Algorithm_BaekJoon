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
 *	백준 11408번: 열혈강호 5
 *
 *	@see https://www.acmicpc.net/problem/11408/
 *
 */
public class Boj11408 {
	private static ArrayList<Integer>[] connected;
	private static int[][] flow;
	private static int[][] dist;
	private static int[][] capacity;
	private static int[] prev;
	
	private static final int INF = 1_000_000_001;
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int leng = N + M + 2;
		
		connected = new ArrayList[leng];				// source: N * M, sink: N * M + 1
		for(int i = 0; i < leng; i++) {
			connected[i] = new ArrayList<>();
		}
		
		capacity = new int[leng][leng];
		dist = new int[leng][leng];
		flow = new int[leng][leng];
		
		int source = N + M;
		int sink = N + M + 1;
		
		for(int i = 0; i < N; i++) {			// 소스와 인접한 일꾼들 연결
			capacity[source][i] = 1;
			
			connected[source].add(i);
			connected[i].add(source);
		}
		
		for(int i = N; i < M + N; i++) {		// 싱크와 인접한 작업들 연결
			capacity[i][sink] = 1;
			
			connected[sink].add(i);
			connected[i].add(sink);
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			
			while(count-- > 0) {
				int work = Integer.parseInt(st.nextToken()) + N - 1;
				int cost = Integer.parseInt(st.nextToken());
				
				connected[i].add(work);
				connected[work].add(i);
				
				dist[i][work] = cost;
				dist[work][i] = -cost;
				
				capacity[i][work] = 1;			// 이분 매칭이므로 유량 1로 통일
			}
		}
		
		System.out.println(networkFlow(leng, source, sink));
	}
	
	private static StringBuilder networkFlow(int size, int S ,int T) {
		StringBuilder sb = new StringBuilder();
		int result = 0, count = 0;
		
		while(true) {
			prev = new int[size];
			spfa(size, S);				// SPFA를 통해 움직일 수 있는 최단 경로 찾기
			
			if(prev[T] == -1) break;
			
			int mFlow = INF;
			for(int i = T; i != S; i = prev[i]) {			// 이분 매칭에서는 필요 없는 코드, 이분 매칭이므로 mFlow는 항상 1
				mFlow = Math.min(mFlow, capacity[prev[i]][i] - flow[prev[i]][i]);
			}
			
			for(int i = T; i != S; i = prev[i]) {
				result += mFlow * dist[prev[i]][i];
				flow[prev[i]][i] += mFlow;
				flow[i][prev[i]] -= mFlow;
			}
			
			count++;
		}
		
		return sb.append(count).append(NEW_LINE).append(result);
	}
	
	private static void spfa(int size, int S) {
		boolean[] inQ = new boolean[size];
		int[] cost = new int[size];
		
		Arrays.fill(prev, -1);
		Arrays.fill(cost, INF);
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(S);
		inQ[S] = true;
		cost[S] = 0;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			inQ[current] = false;
			
			for(int next: connected[current]) {
				// 유량이 흐를 수 있고, 최단 경로인 경우
				if(capacity[current][next] - flow[current][next] > 0 && cost[next] > cost[current] + dist[current][next]) {
					cost[next] = cost[current] + dist[current][next];
					prev[next] = current;
					
					if(!inQ[next]) {
						q.offer(next);
						inQ[next] = true;
					}
				}
			}
		}
	}
}
