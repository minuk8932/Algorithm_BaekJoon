package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 3010번: 페그
 *
 *	@see https://www.acmicpc.net/problem/3010/
 *
 */
public class Boj3010 {
	private static final char SPACE = ' ';
	private static final char EMPTY = '.';
	private static final char CHIP = 'o';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		char[][] peg = init();			// 입력
		System.out.print(search(peg));	// 결과 출력
	}
	
	private static char[][] init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] input = new char[7][7];
		
		for(int i = 0; i < input.length; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < input[i].length; j++) {
				input[i][j] = line.charAt(j);
			}
		}
		
		return input;
	}
	
	private static int search(char[][] map) {
		int counts = 0;
		
		for(int row = 0; row < map.length; row++) {
			for(int col = 0; col < map[row].length; col++) {
				if(isHere(map[row][col], EMPTY)|| isHere(map[row][col], SPACE)) continue;
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = nextDirection(row, DIRECTION[ROW]);		// 바로 인접한 다음 칸 탐색
					int nextCol = nextDirection(col, DIRECTION[COL]);
					
					if(isAppropriateRange(nextRow, nextCol, map.length)) continue;
					if(!isHere(map[nextRow][nextCol], CHIP)) continue;

					int theNextRow = nextDirection(nextRow, DIRECTION[ROW]);		// 같은 방향으로 한칸 더 움직인 후 탐색
					int theNextCol = nextDirection(nextCol, DIRECTION[COL]);
						
					if(isAppropriateRange(theNextRow, theNextCol, map.length)) continue;
					if(!isHere(map[theNextRow][theNextCol], EMPTY)) continue;
				
					counts++;	// 경우의 수 +1
				}
			}
		}
		
		return counts++;
	}
	
	private static boolean isAppropriateRange(int row, int col, int limit) {		// 범위를 벗어 나는가?
		if(row < 0 || row >= limit || col < 0 || col >= limit) return true;
		return false;
	}
	
	private static boolean isHere(char origin, char compare) {			// 이 지점에 내가 원하는것이 있는가?
		return origin == compare ? true : false;
	}
	
	private static int nextDirection(int current, int direction) {
		return current + direction;
	}
}

