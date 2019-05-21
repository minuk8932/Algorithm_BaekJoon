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
 *	백준 17222번: 위스키 거래
 *
 *	@see https://www.acmicpc.net/problem/17222/
 *
 */
public class Boj17222 {
	private static ArrayList<Integer>[] connected;
	private static int[][] capacity;
	private static int[][] flow;
	
	private static final int INF = 1_000_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int size = N + M + 2, src = N + M, snk = N + M + 1;
		
		connected = new ArrayList[size];
		capacity = new int[size][size];
		flow = new int[size][size];
		
		for(int i = 0; i < size; i++) {
			connected[i] = new ArrayList<>();
		}
		
		int[] friend = new int[src];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			friend[i] = Integer.parseInt(st.nextToken());
			connected[i].add(snk);
			connected[snk].add(i);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = N; i < src; i++) {
			friend[i] = Integer.parseInt(st.nextToken());
			connected[i].add(src);
			connected[src].add(i);
		}
		
		for(int mf = N; mf < src; mf++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			
			while(count-- > 0) {
				int jf = Integer.parseInt(st.nextToken()) - 1;
				
				connected[jf].add(mf);
				connected[mf].add(jf);
				capacity[mf][jf] = friend[jf];		// 친구끼리 전할 수 있는 위스키 갯수
				capacity[jf][snk] = INF;			// 전달 가능한 최대 위스키 수를 sink로
			}
			
			capacity[src][mf] = friend[mf];
		}
		
		System.out.println(networkFlow(size, src, snk));
	}
	
	private static int networkFlow(int N, int S, int T) {
		int whiskey = 0;
		int[] prev = new int[N];
		
		while(true) {
			Arrays.fill(prev, -1);
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(S);
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next: connected[current]) {
					if(prev[next] != -1) continue;

					if(capacity[current][next] - flow[current][next] > 0) {
						prev[next] = current;
						
						q.offer(next);
					}
				}
			}
			
			if(prev[T] == -1) break;
			
			int minFlow = INF;
			for(int i = T; i != S; i = prev[i]) {
				minFlow = Math.min(minFlow, capacity[prev[i]][i] - flow[prev[i]][i]);
			}
			
			for(int i = T; i != S; i = prev[i]) {		// 보낸 위스키만큼 차단
				flow[prev[i]][i] += minFlow;
				flow[i][prev[i]] -= minFlow;
			}
			
			whiskey += minFlow;
		}
		
		return whiskey;
	}
}
