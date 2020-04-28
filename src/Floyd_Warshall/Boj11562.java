package Floyd_Warshall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11562번 : 백양로 브레이크
 *
 *	@see https://www.acmicpc.net/problem/11562/
 *
 */
public class Boj11562 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int INF = 1_000_000;
	private static final int ONE_WAY = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n + 1][n + 1];
		for(int i = 0; i < n + 1; i++){
			Arrays.fill(map[i], INF);
		}
		
		for(int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(b == ONE_WAY){						// 단방향 길일 경우, 길이 존재하면 0, 존재하지 않는다면 길을 놓아야 하므로 1로 채워줌
				map[u][v] = 0;
				map[v][u] = 1;
			}
			else{											// 양방향의 경우 따로 길의 생성이 필요없으므로, 0으로 초기화
				map[u][v] = map[v][u] = 0;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int k = Integer.parseInt(br.readLine());
		
		floydWashall(map, n);						// 플로이드 와샬 알고리즘 실행
		
		while(k-- > 0){															// 테스트 케이스만큼 실행
			st = new StringTokenizer(br.readLine(), SPACE);
			int s = Integer.parseInt(st.nextToken());					// 출발지
			int e = Integer.parseInt(st.nextToken()); 				// 도착지
			
			if(s == e){																// 출발지 == 도착지이면 움직일 필요가 없으므로 0;
				sb.append(0).append(NEW_LINE);			
			}
			else{																		// 그외에는 해당 조건에 맞는 배열을 불러와서 버퍼에 담아줌
				sb.append(map[s][e]).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());								// 결과값 한번에 출력
	}
	
	/**
	 * 	플로이드 와샬 알고리즘 메소드
	 * 
	 * 	@param map : 플로이드 와샬을 진행시킬 배열
	 * 	@param n : 최대 경로의 수
	 */
	private static void floydWashall(int[][] map, int n){
		
		for(int v = 1; v < n + 1; v++){
			for(int s = 1; s < n + 1; s++){
				for(int e = 1; e < n + 1; e++){
					if(map[s][e] > map[s][v] + map[v][e]){			// 중간 경로가 시작점에서 도착점까지의 경로보다 짧게 걸린다면,
						map[s][e] = map[s][v] + map[v][e];				// 시작 도착점 경로를 중간경로에 걸쳐 가는 지름길로 변경
					}
				}
			}
		}
	}
}
