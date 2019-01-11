package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2446번: 별찍기 - 9
 *
 *	@see https://www.acmicpc.net/problem/2446/
 *
 */
public class Boj2446 {
	private static final char STAR = '*';
	private static final char SPACE = ' ';
	private static final char NEW_LINE = '\n';
	
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		staring(N, 0, 2 * N - 2, 1);
		
		midFill(N - 1, STAR);			// 중앙 별
		
		staring(N, 2 * N - 3, -1, -1);
		
		System.out.print(sb);		// 규칙에 따라 별을 찍어보자
	}
	
	private static void midFill(int loop, char next) {
		while(loop-- > 0) sb.append(SPACE);
		sb.append(next).append(NEW_LINE);
	}
	
	private static void staring(int N, int start, int end, int diff) {
		for(int i = start; (i * diff) < (end * diff); i += (2 * diff)) {
			int loop = 2 * N + ((diff + 1) / 2 * -1) - i;				// 반복 공식
			
			addSpace(i / 2);
			addStar(loop);
			sb.append(NEW_LINE);
		}
	}
	
	private static void addSpace(int loop) {
		while(loop-- > 0) sb.append(SPACE);
	}
	
	private static void addStar(int loop) {
		while(loop-- > 0) sb.append(STAR);
	}
}
