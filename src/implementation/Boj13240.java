package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 13240번: Chess Board
 *
 *	@see https://www.acmicpc.net/problem/13240/
 *
 */
public class Boj13240 {
	private static final String[] BOARD = {"*.*.*.*.*.", ".*.*.*.*.*"};
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		System.out.print(getBoard(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
	}
	
	private static String getBoard(int N, int M) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++) {			// 보드 생성		
			for(int j = 0; j < M; j++) {
				sb.append(BOARD[i % 2].charAt(j));
			}
			sb.append(NEW_LINE);
		}
		
		return sb.toString();
	}
}
