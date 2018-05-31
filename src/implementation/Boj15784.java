package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15784번: 전투진서
 *
 *	@see https://www.acmicpc.net/problem/15784/
 *
 */
public class Boj15784 {
	private static final String HAPPY = "HAPPY";
	private static final String ANGRY = "ANGRY";
	
	private static int N = 0;
	private static int[][] map = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j < N + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println((chkRow(a, b) && chkCol(a, b)) ? HAPPY : ANGRY);	// 두 메소드 값이 모두 참이면 HAPPY 그외 ANGRY 출력
	}
	/**
	 * 진서의 앞뒤로 잘생긴 사람이 있는지 확인
	 * @param 진서의 위치
	 * @return 진서보다 잘생긴 사람이 없으면 true 아니면 false
	 */
	private static boolean chkRow(int x, int y) {
		for(int i = 1; i < N + 1; i++) {
			if(i == x) continue;
			
			if(map[i][y] > map[x][y]) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 진서의 양옆으로 잘생긴 사람이 있는지 확인
	 * @param 진서의 위치
	 * @return 진서보다 잘생긴 사람이 없으면 true 아니면 false
	 */
	private static boolean chkCol(int x, int y) {
		for(int i = 1; i < N + 1; i++) {
			if(i == y) continue;
			
			if(map[x][i] > map[x][y]) {
				return false;
			}
		}
		
		return true;
	}
}
