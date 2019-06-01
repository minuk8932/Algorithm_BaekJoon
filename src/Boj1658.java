import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1658 {
	private static final int INF = 1_000_000;
	
	private static ArrayList<Integer>[] connected;
	private static int[][] capacity;
	private static int[][] flow;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int size = N + M + 2, src = size - 2, snk = size - 1;
		connected = new ArrayList[size];
		capacity = new int[size][size];
		flow = new int[size][size];
		
		for(int i = 0; i < size; i++) {
			connected[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int cage = 0; cage < N; cage++) {
			int pig = Integer.parseInt(st.nextToken());
			
			connected[src].add(cage);
			connected[cage].add(src);
			
			capacity[src][cage] = pig;
		}
		
		for(int guest = N; guest < src; guest++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			
			while(count-- > 0) {
				int cageNo = Integer.parseInt(st.nextToken()) - 1;
				
				connected[cageNo].add(guest);
				connected[guest].add(cageNo);
				
				capacity[cageNo][guest] = INF;
			}
			
			int buy = Integer.parseInt(st.nextToken());
			
			connected[snk].add(guest);
			connected[guest].add(snk);
			
			capacity[guest][snk] = buy;
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
			
			for(int i = T; i != S; i = prev[i]) {
				flow[prev[i]][i] += minFlow;
				flow[i][prev[i]] -= minFlow;
			}
			
			result += minFlow;
		}
		
		return result;
	}
}
