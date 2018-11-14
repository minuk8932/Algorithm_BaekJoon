package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 		@author minchoba
 *		백준 1753번 : 최단경로
 *
 *		@see https://www.acmicpc.net/problem/1753
 *
 */
public class Boj1753 {
	private static final String SPACE = " ";
	private static final String NO_WAY = "INF";
	private static final String NEW_LINE = "\n";
	private static final int INF = 10_000_001;
	
	private static ArrayList<Point>[] graph;
	
	public static void main(String[] args) throws Exception {
		// 버퍼릁 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(br.readLine());		// start
		
		graph = new ArrayList[V + 1];
		for(int i = 0; i < V + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		while(E-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[u].add(new Point(v, w));
		}
		
		dijkstra(V, s);
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
	 *
	 */
	static class Point implements Comparable<Point> {
		int v, w;

		public Point(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Point point) {
			if(this.w < point.w) return -1;
			else if(this.w > point.w) return 1;
			else return 0;
		}
	}
	
	/**
	 * 다익스트라 알고리즘
	 * 
	 * @param N
	 * @param start
	 */
	private static void dijkstra(int N, int start) {
		int[] cost = new int[N + 1];
		Arrays.fill(cost, INF);			// 최솟값 탐색을 위해 최대 비용으로 초기화
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		cost[start] = 0;
		
		pq.offer(new Point(start, cost[start]));
			
		while(!pq.isEmpty()) {
			Point current = pq.poll();
				
			for(Point next: graph[current.v]) {
				if(cost[next.v] > cost[current.v] + next.w) {	// 다음 정점까지 비용이 이제까지 온 비용보다 크면 값 갱신
					cost[next.v] = cost[current.v] + next.w;
						
					pq.offer(new Point(next.v, cost[next.v]));
				}
			}
		}
		
		System.out.println(output(cost));		// output 메소드를 통한 답 출력
	}
	
	/**
	 * 결과 출력 메소드
	 * 
	 * @param res
	 * @return
	 */
	private static String output(int[] res) {
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < res.length; i++) {
			if(res[i] == INF) sb.append(NO_WAY);
			else sb.append(res[i]);
			
			sb.append(NEW_LINE);
		}
		
		return sb.toString();
	}
}
