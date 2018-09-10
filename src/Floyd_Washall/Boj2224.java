package Floyd_Washall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2224번: 명제 증명
 *
 *	@see https://www.acmicpc.net/problem/2224/
 *
 */
public class Boj2224 {
	private static final String DELIMETER = " => ";
	private static final String NEW_LINE = "\n";
	
	private static final int ALPHA_MAX = 60;
	private static final char HEAD = 'A';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		boolean[][] map = new boolean[ALPHA_MAX][ALPHA_MAX];
		
		for(int i = 0; i < ALPHA_MAX; i++) map[i][i] = true;
		
		for(int i = 0; i < N; i++) {			
			StringTokenizer st = new StringTokenizer(br.readLine(), DELIMETER);
			char from = st.nextToken().charAt(0);
			char to = st.nextToken().charAt(0);
			
			map[from - HEAD][to - HEAD] = true;
		}
		
		for(int v = 0; v < ALPHA_MAX; v++) {
			for(int s = 0; s < ALPHA_MAX; s++) {
				for(int e = 0; e < ALPHA_MAX; e++) {			//  참인 명제 2개로 삼단논법을 통해 나머지 참인 명제를 찾음				
					if(map[s][v] && map[v][e]) {
						map[s][e] = true;
					}
				}
			}
		}
		
		ArrayList<Integer>[] res = new ArrayList[ALPHA_MAX];
		for(int i = 0; i < ALPHA_MAX; i++) res[i] = new ArrayList<>();
		
		int cnt = 0;
		
		for(int i = 0; i < ALPHA_MAX; i++) {
			for(int j = 0; j < ALPHA_MAX; j++) {
				if(map[i][j] && i != j) {			// A => A를 제외한 참인 명제를 인접 리스트에 저장
					res[i].add(j);
					cnt++;
				}
			}
		}
		
		sb.append(cnt).append(NEW_LINE);			// 참인 명제의 개수
		
		for(int i = 0; i < ALPHA_MAX; i++) {
			for(int ans: res[i]) {				// 인접리스트의 내용들을 "=>"와 함께 버퍼에 담고
				sb.append((char) (i + HEAD)).append(DELIMETER).append((char) (ans + HEAD)).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}
