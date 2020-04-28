package Floyd_Warshall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 1085번: 친구
 *
 *	@see https://www.acmicpc.net/problem/1085/
 *
 */
public class Boj1058 {
	private static final char YES = 'Y';
	private static final int INF = 10_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {		// 무한대 상수로 초기화
			Arrays.fill(map[i], INF);
		}
				
		for(int i = 0; i < N; i++) {
			String line = br.readLine();

			for(int j = 0; j < N; j++) {
				if(line.charAt(j) == YES) map[i][j] = 1;
			}
		}
		
		for(int v = 0; v < N; v++) {			// 플로이드 와샬 알고리즘 수행
			for(int s = 0; s < N; s++) {
				if(v == s) continue;
				for(int e = 0; e < N; e++) {
					if(s == e) continue;
					
					if(map[s][e] > map[s][v] + map[v][e]) map[s][e] = map[s][v] + map[v][e];
				}
			}
		}
		
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			int cnt = 0;
			
			for(int j = 0; j < N; j++) {			// 2 - 친구인 사람 중 가장 많은 친구를 가진 사람의 친구 수를 max에 저장
				if(map[i][j] <= 2 && map[i][j] > 0) cnt++;
			}
			
			if(cnt > max) max = cnt;
		}
		
		System.out.println(max);		// 결과값 출력
	}
}
