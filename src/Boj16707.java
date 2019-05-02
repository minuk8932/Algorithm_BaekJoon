import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16707 {
	private static ArrayList<Integer>[] connected;
	private static int[][] capacity;
	private static int[][] flow;
	private static int[][] dist;
	private static int[] prev;
	private static int[] cost;
	
	private static final int INF = 1_000_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		connected = new ArrayList[N];
		capacity = new int[N][N];
		flow = new int[N][N];
		dist = new int[N][N];
		prev = new int[N];
		
		for(int i = 0; i < N; i++) {
			connected[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			connected[from].add(to);
			connected[to].add(from);
			
			capacity[from][to] = cost;
			dist[from][to] = cost;
			dist[to][from] = -cost;
		}
		
		System.out.println(networkFlow(N));
	}
	
	private static int networkFlow(int n) {
		int result = 0;
		
		while(true) {
			cost = new int[n];
			Arrays.fill(prev, -1);
			Arrays.fill(cost, INF);
				
			cost[0] = 0;
			spfa(n, 0, 1);
			spfa(n, 1, n - 1);
			
			for(int i = 0; i < n; i++) {
				System.out.print(prev[i] + " ");
			}
			
			if(prev[n - 1] == -1) break;
				
			int min = INF;
			for(int i = n - 1; i != 0; i = prev[i]) {
				min = Math.min(min, capacity[prev[i]][i] - flow[prev[i]][i]);
			}
				
			for(int i = n - 1; i != 0; i = prev[i]) {
				result += min * dist[prev[i]][i];
				flow[prev[i]][i] += min;
				flow[i][prev[i]] -= min;
			}
		}
		
		return result;
	}
	
	private static void spfa(int n, int start, int end) {
		boolean[] inQ = new boolean[n];
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		inQ[start] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			inQ[current] = false;
			
			for(int next: connected[current]) {
				if(cost[next] > cost[current] + dist[current][next] && capacity[current][next] - flow[current][next] > 0) {
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
