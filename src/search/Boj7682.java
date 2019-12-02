package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 7682번: 틱택토
 *
 *	@see https://www.acmicpc.net/problem/7682/
 *
 */
public class Boj7682 {
	private static final String TERMINATE = "end";
	private static final String NEW_LINE = "\n";
	private static final String V = "valid";
	private static final String I = "invalid";
	
	private static final char WILD_CARD = '.';
	private static final char FIRST = 'X';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {1, 1}, {1, -1}};
	private static final int ROW = 0, COL = 1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String input = br.readLine();
			if(TERMINATE.equals(input)) break;
			
			sb.append(isValid(input.toCharArray()) ? V: I).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static boolean isValid(char[] board) {	
		int[] count = new int[2];
		
		for(int i = 0; i < board.length; i++) {
			if(board[i] == WILD_CARD) continue;
			
			if(board[i] == FIRST) count[0]++;
			else count[1]++;
		}
		
		if(count[0] < count[1] || count[0] - count[1] > 1) return false;				// count error
		
		int[] flag = new int[2];
		
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				if(row + col >= 3) continue;
				
				int idx = row * 3 + col;
				
				char target = board[idx];
				if(target == WILD_CARD) continue;
				
				for(final int[] DIRECTION: DIRECTIONS) {						// searching
					int bingo = 1;
					
					for(int time = 1; time < 3; time++) {
						int nextRow = row + DIRECTION[ROW] * time;
						int nextCol = col + DIRECTION[COL] * time;
						
						if(nextRow < 0 || nextRow >= 3 || nextCol < 0 || nextCol >= 3) continue;
						if(board[nextRow * 3 + nextCol] == target) bingo++;
					}
					
					if(bingo == 3) {											// is bingo
						if(target == FIRST) flag[0]++;
						else flag[1]++;
					}
				}
			}
		}
		
		if(flag[0] >= 1 && flag[1] >= 1) return false;							// double bingo
		
		if(flag[0] > 0 && flag[1] == 0) return count[0] > count[1];				// miss put
		if(flag[1] > 0 && flag[0] == 0) return count[0] == count[1];
		if(flag[0] == 0 && flag[1] == 0) return count[0] + count[1] == 9;		// less count
		
		return true;
	}
}
