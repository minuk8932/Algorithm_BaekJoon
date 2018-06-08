package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9455번: 박스
 *
 *	@see https://www.acmicpc.net/problem/9455/
 *
 */
public class Boj9455 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int map[][] = new int[n][m];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int res = drop(map, n, m);		// 박스를 떨어트린 횟수를 메소드틀 통해 구함
			
			sb.append(res).append(NEW_LINE);	// 각 케이스마다 회차를 버퍼에 담은 후
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 박스 낙하 메소드
	 * @param map: 박스의 위치
	 * @return: 총 박스가 떨어진 칸의 수
	 */
	private static int drop(int[][] map, int n, int m) {
		int cnt = 0;
		for(int i = m - 1; i >= 0; i--) {		// 왼쪽열부터 행을 아래부터 탐색
			int full = n - 1;					// 초기 빈칸의 위치
			
			for(int j = n - 1; j >= 0; j--) {
				if(map[j][i] == 1) {			// 박스가 있는경우
					cnt += full - j;			// 박스가 없는 칸부터 현 박스위치까지의 값을 뺀 후
					full--;					// 박스가 하나 찼으므로 한칸 줄여줌
				}
			}
		}
		
		return cnt;
	}
}
