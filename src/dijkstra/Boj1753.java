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

	private static final int LIMIT = 200_001;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(br.readLine());

		ArrayList<ArrayList<Point>> map = new ArrayList<>();	// V의 최댓값이 20000 : 2차원 배열로 하면 런타임 에러 발생! -> 유사한 계산을 위한 ArrayList of ArrayList 선언
		int cost[] = new int[V + 1];											// 각 정점에 해당하는 최소비용을 담을 배열
		
		for(int i = 0; i < V + 1; i++){
			map.add(new ArrayList());										// 내부 ArrayList 초기화
		}
		
		Arrays.fill(cost, LIMIT);

		while (E-- > 0) {
			st = new StringTokenizer(br.readLine(), SPACE);
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());

			map.get(node1).add(new Point(node2, val));			// node1의 값에 Point 클래스를 이용해 node2, val 즉, 향하는 정점의 값과 해당 경로의 비용을 담아줌
		}
		
		int saveV = V;
		
		cost[s] = 0;

		// 우선순위 큐를 이용한 시작 정점에서부터 각 정점까지의 최단 경로를 계산
		while (V-- > 0) {
			PriorityQueue<Point> pq = new PriorityQueue<>();
			pq.add(new Point(s, cost[s]));								// 시작 정점에 해당하는 값들을 우선순위큐에 담아준다.

			while (!pq.isEmpty()) {
				int currentV = pq.poll().v;								// 현재 정점의 값을 정수형으로 이용해야하므로 정수형 currentV를 선언 후 해당 정점에 대한 값만 poll()										

				for (Point p : map.get(currentV)) {					// 다음 시작 정점과 다음 목적 정점을 받아오기 위한 반복문
					int nextV = p.v;
					int nextW = p.w;
					
					if (cost[nextV] > cost[currentV] + nextW) {		// 다음 정점까지의 비용이 현재까지 정점의 비용과 다음 정점으로 가는 경로에 해당하는 비용보다 크다면 
						cost[nextV] = cost[currentV] + nextW;		// 값을 갱신

						pq.add(new Point(nextV, cost[nextV]));		// 다음 정점에 대한 내용을 Point 클래스를 통해 우선순위 큐에 담아줌
					}
				}
			}
		}
		
		// 각 정점 별로 최소 가중치를 도출
		
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i < saveV + 1; i++) {
			if (cost[i] == LIMIT) {									// 최단경로가 없다면 INF 출력
				sb.append(NO_WAY).append(NEW_LINE);
			} else {
				sb.append(cost[i]).append(NEW_LINE);
			}
		}

		System.out.println(sb.toString());
	}
	
	static class Point implements Comparable<Point> {
		int v, w;

		public Point(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Point point) {
			return (w < point.w) ? -1 : 1;		// this.w의 값이 더 작다면 우선순위를 1로 변경해준다.
		}
	}
}
