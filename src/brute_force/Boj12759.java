package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12759번: 틱! 택! 토!
 *
 *	@see https://www.acmicpc.net/problem/12759/
 *
 */
public class Boj12759 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int starter = Integer.parseInt(br.readLine());		// 시작턴
		int another = starter == 1 ? 2 : 1;				// 다음턴
		
		int[][] map = new int[4][4];
		int turn = 0;
		
		boolean isWin = false;
		
		for(int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			if(i % 2 == 0) {		// 들어오는 순서대로 해당 턴의 번호를 담고, 각 입력마다 누구 턴이었는지 기록
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = starter;
				turn = starter;
			}
			else {
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = another;
				turn = another;
			}
			
			isWin = process(map, turn);	// 프로세스 메소드를 통해 승리한 사람이 있는지 체크
			if(isWin) break;			// 승리한 사람이 있다면 반복문 종료
		}
		
		System.out.println(isWin ? turn : 0);		// 승리한 사람이 있다면 그 사람의 턴을 출력하고, 아니면 0
	}
	
	/**
	 * 승자 확인 프로세스 메소드
	 * 
	 */
	private static boolean process(int[][] board, int t) {	
		// 총 8방향으로 0이아닌 같은 숫자로 빙고가 생겼는지 확인 후 값을 반환
		for(int i = 1; i < 4; i++) {			
			if(board[i][1] == board[i][2] && board[i][2] == board[i][3] && t == board[i][1] && t == board[i][2] && t == board[i][3]) return true;
			if(board[1][i] == board[2][i] && board[2][i] == board[3][i] && t == board[1][i] && t == board[2][i] && t == board[3][i]) return true;
		}
		
		if(board[1][1] == board[2][2] && board[2][2] == board[3][3] && t == board[2][2] && t == board[1][1] && t == board[3][3]) return true;
		if(board[3][1] == board[2][2] && board[2][2] == board[1][3] && t == board[2][2] && t == board[3][1] && t == board[1][3]) return true;
		
		return false;
	}
}
