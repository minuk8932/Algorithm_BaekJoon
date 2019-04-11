package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1658 {
	private static ArrayList<Integer>[] connected;
	private static int[][] capacity;
	private static int[][] flow;
	private static long[][] dist;
	private static int[] prev;
	
	private static final long INF = Long.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int src = N + M, length = src + 2, snk = src + 1;
		
		connected = new ArrayList[length];
		capacity = new int[length][length];
		flow = new int[length][length];
		dist = new long[length][length];
		
		for(int i = 0; i < length; i++) {
			connected[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int cage = N; cage < N + M; cage++) {
			int cost = Integer.parseInt(st.nextToken());
			
			connected[cage].add(snk);
			connected[snk].add(cage);
			capacity[cage][snk] = cost;
//			dist[cage][snk] = cost;
//			dist[snk][cage] = -cost;
		}
		
		for(int guest = 0; guest < N; guest++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			
			while(count-- > 0) {
				int key = Integer.parseInt(st.nextToken()) + N - 1;
				connected[guest].add(key);
				connected[key].add(guest);
				capacity[guest][key] = 1;
//				dist[guest][key] = 1;
//				dist[key][guest] = -1;
			}
			
			int cost = Integer.parseInt(st.nextToken());
			
			connected[src].add(guest);
			connected[guest].add(src);
			capacity[src][guest] = cost;
			dist[src][guest] = cost;
			dist[guest][src] = -cost;
		}
		
		System.out.println(networkFlow(length, src, snk));
	}
	
	private static int networkFlow(int size, int source, int sink) {
		int result = 0;
		prev = new int[size];
		
		while(true) {
			spfa(size, source);
			
			if(prev[sink] == -1) break;
			
			for(int i = sink; i != source; i = prev[i]) {
				result += dist[prev[i]][i];
				flow[prev[i]][i] += 1;
				flow[i][prev[i]] -= 1;
			}
		}
		
		return result;
	}
	
	private static void spfa(int size, int source) {
		boolean[] inQ = new boolean[size];
		long[] cost = new long[size];
		
		Arrays.fill(prev, -1);
		Arrays.fill(cost, INF);
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(source);
		
		inQ[source] = true;
		cost[source] = 0;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			inQ[current] = false;
			
			for(int next: connected[current]) {
				if(capacity[current][next] - flow[current][next] > 0 && cost[next] > cost[current] + dist[current][next]) {
					cost[next] = cost[current] + dist[current][next];
					prev[next] = current;
					
					if(!inQ[next]) {
						inQ[next] = true;
						q.offer(next);
					}
				}
			}
		}
	}
}
