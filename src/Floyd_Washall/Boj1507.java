package Floyd_Washall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1507번: 궁금한 민호
 *
 *	@see https://www.acmicpc.net/problem/1507/
 *
 */
public class Boj1507 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		int[][] min = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min[i][j] = map[i][j];
			}
		}
		
		int res = 0;
		boolean chk = false;
		
		for(int v = 0; v < N; v++) {				// 이미 최소로 만든 경로에 다시 최단경로가 존재하는 경우 확인
			for(int s = 0; s < N; s++) {
				for(int e = 0; e < N; e++) {
					if(min[s][e] > min[s][v] + min[v][e]) {
						chk = true;
					}
				}
			}
		}
		
		if(!chk) {
			for(int v = 0; v < N; v++) {
				for(int s = 0; s < N; s++) {
					for(int e = 0; e < N; e++) {					// 그 외 최단 거리를 제거해줌
						if(map[s][e] >= map[s][v] + map[v][e]) {		
							if(map[s][v] != 0 && map[v][e] != 0) {
								map[s][e] = 0;
							}
						}
					}
				}
			}
			
			for(int i = 0; i < N; i++) {		// 남은 경로의 합
				for(int j = 0; j < N; j++) {
					res += map[i][j];
				}
			}
		}

		System.out.println(chk ? -1 : res / 2);		// 경로가 다시 줄어든 경우 -1, 그 외 경로의 합
	}
}
