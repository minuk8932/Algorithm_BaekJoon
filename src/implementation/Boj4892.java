package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4892번: 숫자 맞추기 게임
 *
 *	@see https://www.acmicpc.net/problem/4892/
 *
 */
public class Boj4892 {
	private static final String NEW_LINE = "\n";
	private static final String ODD = ". odd ";
	private static final String EVEN = ". even ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int count = 1;
		
		while(true) {
			int n0 = Integer.parseInt(br.readLine());
			if(n0 == 0) break;
			
			if(n0 % 2 == 0) sb.append(count++).append(EVEN);		// 3을 곱해도 짝수는 짝수, 홀수는 홀수
			else sb.append(count++).append(ODD);
			
			sb.append((((3 * n0 + 1)/ 2) * 3) / 9).append(NEW_LINE);	// n4 구하는 공식
		}
		
		System.out.println(sb);
	}
}
