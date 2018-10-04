package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1941번: 소문난 칠공주
 *
 *	@see https://www.acmicpc.net/problem/1941/
 *
 */
public class Boj1941 {	
	private static final int SIZE = 5;
	private static final int FULL_MEMBER = 7;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final char SOM = 'S';
	
	private static char[][] map = new char[SIZE][SIZE];
	private static boolean[] isVisited = new boolean[SIZE * SIZE];
	private static boolean[][] mapCheck = new boolean[SIZE][SIZE];
	
	private static int counts = 0;
	private static int result = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		for(int i = 0; i < SIZE; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < SIZE; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for(int i = 0; i < SIZE * SIZE; i++) {
			isVisited = new boolean[SIZE * SIZE];
			mapCheck = new boolean[SIZE][SIZE];
			
			dfs(i, 1, 0);			// 백트래킹 메소드
		}
		
		System.out.println(result);	// 결과 값 출력
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
	
	private static void dfs(int start, int count, int sCnt) {
		int row = start / SIZE;			// 행과 열 값
		int col = start % SIZE;
		
		if(map[row][col] == SOM) sCnt++;		// 맵의 값이 'S'인 경우 다솜팀 +1
		
		isVisited[start] = true;
	    mapCheck[row][col] = true;
	 
	    if (count == FULL_MEMBER) {			// 멤버가 7명이 되었을 때
	        if (sCnt >= 4) {				// 다솜팀이 4명 이상인 경우
	        	isSuitable();				// 적절한지 체크하는 메소드
	        }
	    } 
	    
	    else {
	        for (int next = start + 1; next < SIZE * SIZE; next++) {
	            if (isVisited[next]) return;
	            dfs(next, count + 1, sCnt);			// 수가 부족한 경우 다음번째 알파벳 재귀 호출
	        }
	    }
	    
	    isVisited[start] = false;			// 백트래킹
	    mapCheck[row][col] = false;
	}
	
	private static void isSuitable() {
		for(int i = 0; i < SIZE * SIZE; i++) {
			if(isVisited[i]) {
				int row = i / SIZE;
				int col = i % SIZE;
				
				boolean[][] visit = new boolean[SIZE][SIZE];
				visit[row][col] = true;
				counts = 1;
				
				search(new Point(row, col), visit);			// 탐색을 통해 적절한 문자열인지 판단			
				return;
			}
		}
	}
	
	private static void search(Point current, boolean[][] v) {
		if(counts == FULL_MEMBER) {			// 인접한 7문자열인 경우 결과 +1
			result++;
		}
		else {
			for(final int[] DIRECTION: DIRECTIONS) {			// 각 문자열이 인접해있는지 검사
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow >= 0 && nextRow < SIZE && nextCol >= 0 && nextCol < SIZE) {
					if(mapCheck[nextRow][nextCol] && !v[nextRow][nextCol]) {
						v[nextRow][nextCol] = true;
						
						counts++;								// 인접한 경우 +1
						search(new Point(nextRow, nextCol), v);
					}
				}
			}
		}
	}
}
