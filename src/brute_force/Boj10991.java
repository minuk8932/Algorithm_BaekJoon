package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10991번: 별찍기 16
 *
 *	@see https://www.acmicpc.net/problem/10991/
 *
 */
public class Boj10991 {
	private static final char STAR = '*';
	private static final char SPACE = ' ';
	private static final char NEW_LINE = '\n';
	
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N + i; j++) {
	        	int num = (j + i + N) & 1;			// 짝수 == 0, 홀수 > 0
	        	sb.append((j > N - 2 - i) && num > 0 ? STAR : SPACE);
	        }
	        sb.append(NEW_LINE);
	    }
		
		System.out.print(sb);		// 규칙에 따라 별을 찍어보자
	}
}
