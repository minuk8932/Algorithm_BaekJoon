package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9518번: 로마 카톨릭 미사
 *
 *	@see https://www.acmicpc.net/problem/9518/
 *
 */
public class Boj9518 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1 ,1}, {-1 ,-1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final int PERSON = 'o';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		char[][] church = new char[R][S];
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < S; j++) {
				church[i][j] = line.charAt(j);
			}
		}
		
		System.out.println(search(R, S, church));	// 탐색 메소드를 통해 총 악수 횟수 구함
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	/**
	 * 상근이가 앉을 수 있는 적절한 자리를 찾고 미사에 참여한 인원이 악수하는 횟수를 구함
	 * 
	 */
	private static int search(int n, int m, char[][] map) {
		int max = 0;
		
		Point pos = new Point(0, 0);			// 상근이 위치 저장 변수
		
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < m; col++) {
				if(map[row][col] == PERSON) continue;		// 사람이 앉은 곳은 피함
				
				int handShake = 0;
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = row + DIRECTION[ROW];
					int nextCol = col + DIRECTION[COL];
					
					if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
						if(map[nextRow][nextCol] == PERSON) handShake++;
					}
				}
				
				if(handShake > max) {			// 악수를 가장 많이 할 수 있는 자리를 찾아
					max = handShake;
					pos = new Point(row, col);
				}
			}
		}
		
		map[pos.row][pos.col] = PERSON;			// 상근이 위치로 지정
		
		int res = 0;
		boolean[][] isVisited = new boolean[n][m];
		
		for(int row = 0; row < n; row++) {				// 개인 당 악수 횟수가 아닌 전체의 총 악수 횟수를 구하는 것이므로
			for(int col = 0; col < m; col++) {
				if(map[row][col] != PERSON) continue;
				if(isVisited[row][col]) continue;		// 이미 악수 한 사람은 세지 않음
				
				isVisited[row][col] = true;				// 이미 악수 한 사람은 참으로 처리
				int handShake = 0;
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = row + DIRECTION[ROW];
					int nextCol = col + DIRECTION[COL];
					
					if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
						if(isVisited[nextRow][nextCol]) continue;
						if(map[nextRow][nextCol] == PERSON) handShake++;			// 현 위치에서 이미 악수한 사람은 제외하고 악수 횟수를 구해서
					}
				}
				
				res += handShake;		// 결과에 누적 합을 구함
			}
		}
		
		return res;
	}
}
