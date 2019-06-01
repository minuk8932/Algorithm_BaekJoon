package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 12026번: BOJ 거리
 *
 *	@see https://www.acmicpc.net/problem/12026/
 *
 */
public class Boj12026 {
	private static final int INF = 10_000_000;
	
	private static class Path implements Comparable<Path>{
		int node;
		int cost;
		
		public Path(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Path p) {
			return this.cost < p.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Path>[] boj = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			boj[i] = new ArrayList<>();
		}
		
		char[] word = br.readLine().toCharArray();
		
		for(int from = 0; from < N; from++) {				// 경로 생성 가능하면, 경로와 거리 저장
			for(int to = from + 1; to < N; to++) {
				if((word[from] == 'B' && word[to] == 'O') || (word[from] == 'O' && word[to] == 'J') || (word[from] == 'J' && word[to] == 'B')) {
					int pow = (from - to) * (from - to);
					boj[from].add(new Path(to, pow));
				}
			}
		}
		
		System.out.println(getCost(N, boj));
	}
	
	private static int getCost(int n, ArrayList<Path>[] list) {
		int[] cost = new int[n];
		Arrays.fill(cost, INF);
		
		PriorityQueue<Path> pq = new PriorityQueue<>();
		pq.offer(new Path(0, 0));
		
		cost[0] = 0;
		
		while(!pq.isEmpty()) {
			Path current = pq.poll();
			
			for(Path next: list[current.node]) {
				if(cost[next.node] > current.cost + next.cost) {		// 최단 경로 찾아가기
					cost[next.node] = current.cost + next.cost;
					if(next.node == n - 1) break;
					
					pq.offer(new Path(next.node, cost[next.node]));
				}
			}
			
		}
		
		
		return cost[n - 1] == INF ? -1: cost[n - 1];
	}
}
