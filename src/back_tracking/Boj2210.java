package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2210번: 숫자판 점프
 *
 *	@see https://www.acmicpc.net/problem/2210/
 *
 */
public class Boj2210 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;

	private static int[] tmp = new int[6];
	private static int[] isVisited = new int[1 << 20];
	private static int[][] map = new int[5][5];
	private static int res = 0;
	private static int num = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				tmp[0] = map[i][j];
				
				backTracking(1, i, j);		// 백트래킹 메소드를 통한 탐색
				tmp[0] = 0;
			}
		}
		
		System.out.println(res);
	}
	
	/**
	 * 백트래킹 메소드
	 * 
	 */
	private static void backTracking(int depth, int row, int col) {
		if(depth == 6) {		// 숫자가 가득 찬 경우
			num = 0;
			
			for(int i = 0; i < depth; i++) {			// 해당 숫자의 값을 구함
				num += tmp[i] * Math.pow(10, 5 - i);
			}
			
			if((isVisited[num] & 1 << num) == 0) {			// 해당 숫자가 켜진 비트가 아닌 경우
				isVisited[num] = isVisited[num] | 1 << num;		// 켜짐으로 추가하고
				res++;			// 결과값 +1
			}
			
			return;
		}
		
		for(final int[] DIRECTION: DIRECTIONS) {
			int nextRow = row + DIRECTION[ROW];
			int nextCol = col + DIRECTION[COL];
			
			if(nextRow >= 0 && nextRow < 5 && nextCol >= 0 && nextCol < 5) {
				tmp[depth] = map[nextRow][nextCol];				// 해당하는 값을 배열에 담아줌
				backTracking(depth + 1, nextRow, nextCol);		// 재귀 호출
				tmp[depth] = 0;
			}
		}
	}
}
