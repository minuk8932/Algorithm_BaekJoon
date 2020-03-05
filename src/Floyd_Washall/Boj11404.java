package Floyd_Washall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11404번: 플로이드
 *
 *	@see https://www.acmicpc.net/problem/11404/
 *
 */
public class Boj11404 {
	private static final int MAX = 1_000_000_001;
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final int NO_WAY = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int[][] city = new int[n+1][n+1];		// 이동가능한 도시를 담을 배열
		
		for(int i = 0; i < n+1; i++){
			for(int j = 0; j < n+1; j++){			// 도시 배열 초기화
				city[i][j] = MAX;
				
				if(i == j){
					city[i][j] = NO_WAY;
				}
			}
		}
		
		for(int i = 0; i < m; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			if(city[x][y] == MAX){		// 방문하지 않은 도시 배열일 경우
				city[x][y] = cost;
			}
			else{
				if(cost < city[x][y]){	// 이미 방문했지만, 더 작은 값이 있는 경우
					city[x][y] = cost;
				}
			}
		}
		
		for(int v = 1; v < n+1; v++){					// Floyd Washall 알고리즘 수행
			for(int s = 1; s < n+1; s++){
				for(int e = 1; e < n+1; e++){
						city[s][e] = Math.min(city[s][e], city[s][v] + city[v][e]);
				}
			}
		}
		
		for(int i = 0; i < n+1; i++){
			for(int j = 0; j < n+1; j++){	// 최대값보다 크거나 같은 경우 경로 없음(0)을 배열에 담음
				if(city[i][j] >= MAX){
					city[i][j] = NO_WAY;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < n+1; i++){
			for(int j = 1; j < n+1; j++){						// 배열에 담긴 값들을 버퍼에 배열 형식으로 담아줌
				sb.append(city[i][j]).append(SPACE);
			}
			sb.append(NEW_LINE);
		}
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}

}
