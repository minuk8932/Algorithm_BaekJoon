package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4563번: 리벤지 오브 피타고라스
 *
 *	@see https://www.acmicpc.net/problem/4563/
 *
 */
public class Boj4563 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			int A = Integer.parseInt(br.readLine());
			if(A == 0) break;
			
			sb.append(counting(A)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int counting(long a) {
		int count = 0;
		long pow = a * a;
		
		for(int i = 1; i < a; i++) {
			if(pow % i != 0) continue;
			long b = pow / i;				// a로 나누어 떨어지면 우항도 나누어 떨어짐 
			long c = i;
			
			if((b + c) % 2 == 0) {				// 나누어 떨어질 때
				if((b - c) / 2 > a) count++;	// 직각 삼각형
			}
		}
		
		return count;
	}
}
