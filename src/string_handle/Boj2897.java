package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2897번: 몬스터 트럭
 *
 *	@see https://www.acmicpc.net/problem/2897/
 *
 */
public class Boj2897 {
	private static final char NEW_LINE = '\n';
	private static final char BUILDING = '#';
	private static final char CAR = 'X';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] park = new int[5];
		
		char[][] map = new char[R][C];
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for(int i = 0; i < R - 1; i++) {
			for(int j = 0; j < C - 1; j++) {		// 몬스터 트럭이 범위를 벗어나지 않도록	
				boolean isBuilding = false;
				int cnt = 0;
					
				for(int x = i; x <= i + 1; x++) {
					for(int y = j; y <= j + 1; y++) {				// 트럭 범위 내를 탐색					
						if(map[x][y] == BUILDING) isBuilding = true;		// 빌딩이 있다면 참으로
						if(map[x][y] == CAR) cnt++;							// 차량이 있으면 차량 수 +1
					}
				}
					
				if(!isBuilding) park[cnt]++;			// 범위내에 빌딩이 존재하지 않을 경우 차를 부순 개수에 해당하는 배열에 값을 더해줌
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < park.length; i++) {		// 차를 부순 개수에 따른 값을 버퍼에 담고
			sb.append(park[i]).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
}
