package bellman_ford;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1865{
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String WAY = "YES";
	private static final String NO_WAY = "NO";
	
	private static final int INF = 10_000_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			ArrayList<PathCost>[] whole = new ArrayList[M + W + 1];	// 인접 리스트 선언 
			int[] dist = new int[N + W + 1];										// 각 정점 별 비용 배열
			
			for(int i = 0; i < M + W + 1; i++){									// 인접리스트 내부 초기화
				whole[i] = new ArrayList<>();
			}
			Arrays.fill(dist, INF);													// 비용 배열 초기화
			
			for(int i = 1; i < M + W + 1; i++){
				st = new StringTokenizer(br.readLine(), SPACE);
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				if(i < M + 1){															// 웜홀이 아닌 경로 (양방향, 비용 : 양수)
					whole[i].add(new PathCost(start, end, cost));
					whole[i].add(new PathCost(end, start, cost));
				}
				else{																		// 웜홀인 경로 (단방향, 비용 : 음수)
					whole[i].add(new PathCost(start, end, -cost));
				}
			}
			
			dist[1] = 0;																	// 시작점 초기화
			
			if(bellmanF(whole, dist, N, M, W)){								
				sb.append(WAY).append(NEW_LINE);						// 벨만포드 메소드가 참이면
			}
			else{
				sb.append(NO_WAY).append(NEW_LINE);					// 벨만포드 메소드가 거짓이면
			}
		}
		br.close();
		
		System.out.println(sb.toString());									// 결과값 한번에 출력
	}
	
	/**
	 * 
	 * @param pc : 인접리스트, 시작점, 도착점, 이동하는 비용에 대한 값들이 클래스를 통해 저장되어 있음
	 * @param dist : 각 노드별 가중치
	 * @param N : 노드 갯수
	 * @param M : 간선 갯수
	 * @param W : 웜홀 갯수
	 * @return : (전체 경로가 음수 가중치 ? 참 : 거짓) 반환
	 * 
	 */
	private static boolean bellmanF(ArrayList<PathCost>[] pc, int[] dist, int N, int M, int W){
		for(int i = 1; i < N; i++){
			for(int j = 1; j < M + W + 1; j++){
				for(PathCost n : pc[j]){						// 인접리스트의 값을 클래스 변수에 담아 반복문 실행
					if(dist[n.s] == INF){							// 시작점에 해당하는 값이 무한대 일때
						continue;
					}
					
					if(dist[n.e] > dist[n.s] + n.v){			// 도착점의 비용 > 시작점의 비용 + (시작점 -> 도착점) 이동 비용 이면,
						dist[n.e] = dist[n.s] + n.v;			// 도착점의 비용을 더 작은 값으로 설정
						
						if(N - 1 == i){								// 전체 가중치가 음수가 나와서 경로 끝까지 실행했을 경우
							return true;							// 제자리로 왔을 때 비용이 감소해 있음 (0보다 작음)
						}
					}
				}
			}
		}
		
		return false;												// 제자리로 왔을 때 비용이 감소하지 않음 (0보다 작지 않음)
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *	출발, 도착, 비용 이너 클래스
	 *
	 */
	private static class PathCost{
		int s;
		int e;
		int v;
		
		/**
		 * 
		 * @param s : 출발점
		 * @param e : 도착점
		 * @param v : 비용
		 * 
		 */
		public PathCost(int s, int e, int v){
			this.s = s;
			this.e = e;
			this.v = v;
		}
	}
}