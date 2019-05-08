package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12763번: 지각하면 안돼!
 *
 *	@see https://www.acmicpc.net/problem/12763/
 *
 */
public class Boj12763 {
	private static ArrayList<Path>[] list;
	
	private static class Path implements Comparable<Path>{
		int node;
		int time;
		int cost;
		
		public Path(int node, int time, int cost) {
			this.node = node;
			this.time = time;
			this.cost = cost;
		}

		@Override
		public int compareTo(Path p) {
			if(this.time < p.time) {
				return -1;
			}
			else if(this.time > p.time) {
				return 1;
			}
			else {
				if(this.cost < p.cost) return -1;
				else if(this.cost > p.cost) return 1;
				else return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		init(N);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int L = Integer.parseInt(br.readLine());
		
		while(L-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int t = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[from].add(new Path(to, t, c));
			list[to].add(new Path(from, t, c));
		}
		
		System.out.println(dijkstra(N, T, M));
	}
	
	private static void init(int n) {
		list = new ArrayList[n];
		
		for(int i = 0; i < n; i++) {
			list[i] = new ArrayList<>();
		}
	}
	
	private static int dijkstra(int n, int t, int m) {
		PriorityQueue<Path> pq = new PriorityQueue<>();
		
		int[][] cost = new int[n][t + 1];
		for(int i = 0; i < n; i++) {
			Arrays.fill(cost[i], Integer.MAX_VALUE);
		}
		
		pq.offer(new Path(0, 0, 0));
		cost[0][0] = 0;
		
		while(!pq.isEmpty()) {
			Path current = pq.poll();
			if(cost[current.node][current.time] != current.cost) continue;
			
			for(Path next: list[current.node]) {
				int nextTime = current.time + next.time;
				
				if(nextTime <= t && current.cost + next.cost <= m) {					// 가능한 시간과 비용 내에서
					if(cost[next.node][nextTime] > next.cost + current.cost) {			// 어떤 시간 t에서의 최소비용
						cost[next.node][nextTime] = next.cost + current.cost;
						
						pq.offer(new Path(next.node, next.time + current.time, cost[next.node][nextTime]));
					}
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < t + 1; i++) {
			min = Math.min(cost[n - 1][i], min);
		}
		
		return min == Integer.MAX_VALUE ? -1: min;
	}
}
