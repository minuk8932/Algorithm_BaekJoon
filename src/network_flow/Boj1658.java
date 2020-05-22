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
 * @author exponential-e
 * 백준 1658번: 돼지 잡기
 *
 * @see https://www.acmicpc.net/problem/1658/
 *
 */
public class Boj1658 {
	private static final int INF = 1_000_000_000;
	
	private static ArrayList<Integer>[] connected;
	private static int[][] capacity;
	private static int[][] flow;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int size = N + M + 2, src = size - 2, snk = size - 1;
		connected = new ArrayList[size];
		capacity = new int[size][size];
		flow = new int[size][size];
		
		for(int i = 0; i < size; i++) {
			connected[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());

		for(int cage = N; cage < src; cage++) {
			int pig = Integer.parseInt(st.nextToken());

			connected[snk].add(cage);									// cage -> sink
			connected[cage].add(snk);
			
			capacity[cage][snk] = pig;
		}

		ArrayList<Integer>[] key = new ArrayList[src];
		for(int i = 0; i < src; i++){
			key[i] = new ArrayList<>();
		}

		for(int guest = 0; guest < N; guest++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());

			for(int i = 0; i < count; i++) {
				int cage = Integer.parseInt(st.nextToken()) - 1 + N;	// guest -> cage

				connected[cage].add(guest);
				connected[guest].add(cage);

				capacity[guest][cage] = INF;
				key[cage].add(guest);
			}

			int buy = Integer.parseInt(st.nextToken());
			
			connected[src].add(guest);
			connected[guest].add(src);
			
			capacity[src][guest] = buy;
		}

		for(int i = N; i < key.length; i++) {
			int len = key[i].size();
			if(len == 0) continue;

			for(int j = 0; j < len; j++) {
				int one = key[i].get(j);

				for(int k = j + 1; k < len; k++) {						// guest -> guest who has same key
					int another = key[i].get(k);
					connected[one].add(another);
					connected[another].add(one);

					capacity[another][one] = INF;
				}
			}
		}
		
		System.out.println(networkFlow(size, src, snk));
	}
	
	private static int networkFlow(int N, int S, int T) {
		int result = 0;
		int[] prev = new int[N];
		
		while(true) {
			Arrays.fill(prev, -1);
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(S);
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next: connected[current]) {
					if(prev[next] != -1) continue;
					if(capacity[current][next] - flow[current][next] <= 0) continue;
					prev[next] = current;

					if (next == T) break;
					q.offer(next);
				}
			}
			
			if(prev[T] == -1) break;
			
			int minFlow = INF;
			for(int i = T; i != S; i = prev[i]) {
				minFlow = Math.min(minFlow, capacity[prev[i]][i] - flow[prev[i]][i]);
			}
			
			for(int i = T; i != S; i = prev[i]) {
				flow[prev[i]][i] += minFlow;
				flow[i][prev[i]] -= minFlow;
			}
			
			result += minFlow;
		}
		
		return result;
	}
}
