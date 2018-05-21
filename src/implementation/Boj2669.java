package implementation;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

/**
 * 
 * 	@author minchoba
 *	백준 2669번: 직사각형 네개의 합집합 면적 구하기
 *
 *	@see https://www.acmicpc.net/problem/2669/
 *
 */
public class Boj2669 {
	private static final int INF = 101;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] pos = new int[4][4];
		int[][] map = new int[INF][INF];
		
		for(int i = 0; i < 4; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			pos[i][0] = Integer.parseInt(st.nextToken());					// 사각형의 좌표를 받아와서
			pos[i][1] = Integer.parseInt(st.nextToken());
			pos[i][2] = Integer.parseInt(st.nextToken());
			pos[i][3] = Integer.parseInt(st.nextToken());
			
			for(int x = pos[i][0]; x < pos[i][2]; x++){				// 해당 범위 내를 모두 1로 채움
				for(int y = pos[i][1]; y < pos[i][3]; y++){
					map[x][y] = 1;
				}
			}
		}
		
		int sum = 0;
		
		for(int i = 0; i < INF; i++){			// 1이 존재하는 구간이 모든 사각형 넓이 합이므로 모두 더함
			for(int j = 0; j < INF; j++){
				sum += map[i][j];
			}
		}
		
		System.out.println(sum);		// 결과값 출력
	}
}
