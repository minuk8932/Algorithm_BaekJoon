package Floyd_Washall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6135번: Cow Hurdles
 *
 *	@see https://www.acmicpc.net/problem/6135/
 *
 */
public class Boj6135 {
	private static final int INF = -1;
	private static final String NEW_LINE = "\n";
	
	private static StringTokenizer st = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N + 1][N + 1];
		for(int i = 0; i < N + 1; i++) {		// 배열 초기화
			Arrays.fill(map[i], INF);
		}
		
		for(int i = 0; i < M; i++) {					// 경로에 해당하는 값과 비용을 저장
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		String[] tc = new String[T];		// 테스트 케이스 저장
		for(int i = 0; i < T; i++) {
			tc[i] = br.readLine();
		}
		
		System.out.println(floydWashall(N, T, map, tc));		// 프로이드 와샬 메소드를 통한 결과 값 출력
	}
	
	/**
	 * 프로이드 와샬 알고리즘
	 * 
	 */
	private static String floydWashall(int N, int T, int[][] cows, String[] input){
		StringBuilder sb = new StringBuilder();
		
		for(int v = 1; v < N + 1; v++) {
			for(int s = 1; s < N + 1; s++) {
				for(int e = 1; e < N + 1; e++) {
					if(cows[s][e] != INF || (cows[s][v] != INF && cows[v][e] != INF)) {		// 직진 경로나 우회 경로가 존재하는 경우
						if(cows[s][e] == INF) {								// 이 중 직진 경로는 존재하지 않는 경우
							cows[s][e] = Math.max(cows[s][v], cows[v][e]);
						}
						else if (cows[s][e] != INF && cows[s][v] != INF && cows[v][e] != INF) { // 직진과 우회 경로 모두 존재하는 경우
							cows[s][e] = Math.min(Math.max(cows[s][v], cows[v][e]), cows[s][e]);
						}
					}		// 모든 경우는 계산 후 직진 경로에 갱신
				}
			}
		}
		
		for(int i = 0; i < T; i++) {				// 테스트케이스에 해당하는 허들 비용을 버퍼에 저장
			st = new StringTokenizer(input[i]);
			sb.append(cows[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]).append(NEW_LINE);
		}
		
		return sb.toString();
	}
}
