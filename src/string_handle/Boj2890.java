package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2890번: 카약
 *
 *	@see https://www.acmicpc.net/problem/2890/
 *
 */
public class Boj2890 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int INF = 10;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		boolean[] isVisited = new boolean[INF];
		int[] rank = new int[INF];
		int record = 1;
		
		for(int i = C - 2; i > 0; i--) {					// 맨 뒷열부터 탐색하며 가장 앞선 카약을 찾는다
			int cnt = 0;		// 순위를 책정하는 변수
			
			for(int j = 0; j < R; j++) {
				if((map[j][i] >= '1' && map[j][i] <= '9') && !isVisited[map[j][i] - 48]) {	// 카약의 번호에 해당하는 값 중 아직 탐색되지 않은 카약일 때
					isVisited[map[j][i] - 48] = true;		// 해당 카약을 탐색한 것으로 설정 후
					rank[map[j][i] - 48] = record;		// 그 카약의 순위를 배열에 기록
					
					cnt = 1;				// 공동 순위는 무시하므로 다음 카약보다 앞선 카약은 1개로 고정시킴
				}
			}
			
			record += cnt;		// 다음으로 들어오는 카약을 위한 순위 할당
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < INF; i++) {				// 1~9까지 카약의 순위를 버퍼에 담은 후에
			sb.append(rank[i]).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
