package divide_and_conquer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 14624번: 전북대학교
 *
 *	@see https://www.acmicpc.net/problem/14624/
 *
 */
public class Boj14624 {
	private static StringBuilder sb = new StringBuilder();
	
	private static final String EVEN = "I LOVE CBNU";
	private static final String NEW_LINE = "\n";
	private static final char STAR = '*';
	private static final char SPACE = ' ';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(N % 2 == 0 ? EVEN: odd(N));
	}
	
	private static String odd(int n) {		
		for(int i = 0; i < n; i++) {
			sb.append(STAR);
		}
		sb.append(NEW_LINE);
		
		recursion(n, n / 2, n / 2);
		return sb.toString();
	}
	
	private static void recursion(int n, int target1, int target2) {
		if(target1 < 0 || target2 > n - 1) return;			// 끝까지 그림
		
		for(int i = 0; i < target2 + 1; i++) {
			if(i == target2) {								// 목적 인덱스에 따라 따로 처리
				sb.append(STAR).append(NEW_LINE);
				recursion(n, target1 - 1, target2 + 1);
			}
			else if(i == target1) {
				sb.append(STAR);
			}
			else {
				sb.append(SPACE);
			}
		}
	}
}
