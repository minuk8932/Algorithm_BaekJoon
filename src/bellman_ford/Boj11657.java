package bellman_ford;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11657번 : 타임머신
 *
 *	@see https://www.acmicpc.net/problem/11657
 *
 */
public class Boj11657 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	private static final int NO_WAY = -1;
	private static final int INF = 100_001;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		PathCost[] bus = new PathCost[M + 1];
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);												// 각 노드를 무한으로 초기화

		for (int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine(), SPACE);

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			bus[i] = new PathCost(A, B, C);							// 시작, 끝, 비용을 저장한 클래스 배열에 값 입력
		}
		br.close();
		
		dist[1] = 0;																// 문제에서 (시작점 == 1)으로 고정되었으므로, dist[1] 값만 초기화
		StringBuilder sb = new StringBuilder();
		
		if(hasShortestWay(bus, dist, N, M)){						// 벨만-포드 알고리즘 수행
			for(int i = 2; i < N + 1; i++){
				sb.append(dist[i] == INF ? NO_WAY : dist[i]).append(NEW_LINE);		// 만약 dist[i]의 값이 무한대라면, 특정 노드 a -> b로 연결된 간선이 없다는 뜻
			}																									// 무한대가 아니라면, 해당 dist[i]의 값이 곧 최소 비용
		}
		else{
			sb.append(NO_WAY).append(NEW_LINE);											// 벨만-포드 메소드가 false 반환시
		}
		
		System.out.println(sb.toString());														// 결과 값 한번에 출력
	}

	static boolean hasShortestWay(PathCost[] pc, int[] dist, int N, int M) {		
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M + 1; j++) {
				if (dist[pc[j].e] > dist[pc[j].s] + pc[j].v) {										// 만약, 도착지점 노드의 비용 > 출발지점 노드 비용 + (시작-도착)지점까지 걸리는 비용
					dist[pc[j].e] = dist[pc[j].s] + pc[j].v;										// 즉, 비용이 적을 경우에 도착지점 노드의 비용 초기화 : 최솟값 도출
				}
			}
		}

		for (int i = 1; i < M + 1; i++) {
			if (dist[pc[i].e] > dist[pc[i].s] + pc[i].v) {											// 위에서 최솟값 갱신을 했는데도, 다시 최솟값이 발생한 경우 
				return false;																				// 양 노드를 반복하는데 음수의 비용이 발생한 것이므로, false 반환
			}
		}
		
		return true;																						// 위의 코드들이 문제없이 수행 되었을 경우, true 반환
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *	정점 및 비용 이너 클래스
	 *
	 */
	private static class PathCost {
		int s;
		int e;
		int v;
		
		/**
		 * 
		 * 	@param s : 시작노드
		 * 	@param e : 도착노드
		 * 	@param v : 시작-도착노드까지 비용
		 * 
		 */
		public PathCost(int s, int e, int v) {
			this.s = s;
			this.e = e;
			this.v = v;
		}
	}
}
