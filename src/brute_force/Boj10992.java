package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10992번: 별찍기 17
 *
 *	@see https://www.acmicpc.net/problem/10992/
 *
 */
public class Boj10992 {
	private static final char STAR = '*';
	private static final char SPACE = ' ';
	private static final char NEW_LINE = '\n';
	
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N + i; j++) {
	        	if(i == N - 1) {
	        		sb.append(STAR);
	        	}
	        	else {
	        		sb.append(N - 1 - i == j || N - 1 + i == j ? STAR : SPACE);		// 간격에 맞추기
	        	}
	        }
	        sb.append(NEW_LINE);
	    }
		
		System.out.print(sb);		// 규칙에 따라 별을 찍어보자
	}
}
