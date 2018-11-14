package bellman_ford;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	private static final int INF = 100_000_001;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ArrayList<PathCost>[] bus = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) bus[i] = new ArrayList<>();
		
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);

		for (int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine(), SPACE);

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			bus[A].add(new PathCost(B, C));
		}
		br.close();
		
		dist[1] = 0;
		StringBuilder sb = new StringBuilder();
		
		if(hasShortestWay(bus, dist, N)){
			for(int i = 2; i < N + 1; i++){
				sb.append(dist[i] == INF ? NO_WAY : dist[i]).append(NEW_LINE);
			}
		}
		else{
			sb.append(NO_WAY).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	/**
	 * 최단경로 확인: 벨만포드 알고리즘 이용
	 * 
	 * @param pc
	 * @param dist: 최소비용
	 * @param N
	 * @return
	 */
	static boolean hasShortestWay(ArrayList<PathCost>[] pc, int[] dist, int N) {	
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {

				int start = j;
				for(PathCost p: pc[j]) {
					int end = p.e;
					int cost = p.v;
					
					if(dist[end] > dist[start] + cost) {
						if(dist[start] == INF && dist[end] == INF && cost < 0) continue;	// 잘못된 최솟값 갱신시
						dist[end] = dist[start] + cost;
						
						if(i == N) {		// 사이클
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 도착 지점, 비용 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class PathCost {
		int e;
		int v;
		
		public PathCost(int e, int v) {
			this.e = e;
			this.v = v;
		}
	}
}
